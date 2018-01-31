package net.jayde.app.music.dao.mybatisDao;

import net.jayde.app.music.dao.MusicDao;
import net.jayde.app.music.pojo.MusicGroup;
import net.jayde.app.music.pojo.MusicLibrary;
import net.jayde.app.music.pojo.MusicPerson;
import net.jayde.app.music.testMybatis;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MusicDaoMybatis extends MusicDao {
    SqlSession session = null;
    String mapperName = "MusicMapper";

    public SqlSession getSession() {
        return session;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String inputMapperName) {
        this.mapperName = inputMapperName;
    }


    public void setSession(SqlSession inputSession) {
        this.session = inputSession;

        MusicGroupDaoMybatis groupDao = new MusicGroupDaoMybatis();
        groupDao.setSession(session);
        groupDao.setMapperName(mapperName);
        iMusicGroupDao = groupDao;

        MusicPersonDaoMybatis personDao = new MusicPersonDaoMybatis();
        personDao.setSession(session);
        personDao.setMapperName(mapperName);
        iMusicPersonDao = personDao;
    }

    public MusicDaoMybatis() {
        String resource = "MybatisMusicDbConfig.xml";
        InputStream is = testMybatis.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        session = sessionFactory.openSession();
        setSession(session);
    }

    @Override
    public MusicLibrary iniMusicLibrary() {
        MusicLibrary library = new MusicLibrary();
        MusicGroup rootGroup = new MusicGroup("0", "root");
        List<MusicGroup> groups = iMusicGroupDao.getSonMusicGroups(rootGroup.getId());
        for (MusicGroup mg : groups) {
            library.getGroups().add(mg);
            CycleGroup(mg);
        }
        return library;
    }

    private void CycleGroup(MusicGroup group) {
        List<MusicGroup> sonGroups = iMusicGroupDao.getSonMusicGroups(group.getId());
        if (sonGroups != null) {
            if (sonGroups.size() > 0) {
                for (MusicGroup sonGroup : sonGroups) {
                    group.getSonGroups().add(sonGroup);
                    CycleGroup(sonGroup);
                }
            }
        }
        List<MusicPerson> sonPersons = iMusicGroupDao.getSonMusicPersons(group.getId());
        if (sonPersons != null) {
            if (sonPersons.size() > 0) {
                for (MusicPerson sonPerson : sonPersons) {
                    if (sonPerson.getFavourite() == MusicPerson.PERSON_FAVOURITE) {
                        group.getSonPersons().add(sonPerson);
                    }
                }
            }
        }

        return;
    }
}
