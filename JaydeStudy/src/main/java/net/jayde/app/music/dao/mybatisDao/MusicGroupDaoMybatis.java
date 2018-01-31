package net.jayde.app.music.dao.mybatisDao;

import net.jayde.app.music.dao.IMusicGroupDao;
import net.jayde.app.music.pojo.MusicGroup;
import net.jayde.app.music.pojo.MusicPerson;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MusicGroupDaoMybatis implements IMusicGroupDao {
    SqlSession session = null;
    String mapperName = "";

    public SqlSession getSession() {
        return session;
    }

    public void setSession(SqlSession session) {
        this.session = session;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String inputMapperName) {
        this.mapperName = inputMapperName;
    }

    @Override
    public MusicGroup getRootMusicGroup() {
        return null;
    }

    @Override
    public MusicGroup getMusicGroup(String id) {
        System.out.println("-----------------------------");
        System.out.println(session);
        return session.selectOne(mapperName+".getMusicGroup", id);
    }

    @Override
    public List<MusicGroup> getSonMusicGroups(String preId) {
        return session.selectList(mapperName+".getSonMusicGroups",preId);
    }

    @Override
    public List<MusicPerson> getSonMusicPersons(String gId) {
        return session.selectList(mapperName+".getSonMusicPersons",gId);
    }
}
