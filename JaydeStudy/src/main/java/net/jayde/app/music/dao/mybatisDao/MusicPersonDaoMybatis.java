package net.jayde.app.music.dao.mybatisDao;

import net.jayde.app.music.dao.IMUsicPersonDao;
import net.jayde.app.music.pojo.MusicAlbum;
import net.jayde.app.music.pojo.MusicFavourite;
import net.jayde.app.music.pojo.MusicPerson;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class MusicPersonDaoMybatis implements IMUsicPersonDao {
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
    public MusicPerson getMusicPerson(String id) {
        return session.selectOne(mapperName + ".getMusicPerson", id);
    }

    @Override
    public List<MusicAlbum> getSonAlbums(String pId) {
        return session.selectList(mapperName + ".getSonAlbums", pId);
    }

    @Override
    public List<MusicFavourite> getSonFavourites(String pId) {
        return session.selectList(mapperName + ".getSonFavourites", pId);
    }

    @Override
    public void insertMusicPerson(MusicPerson newMusicPerson) {
        session.insert(mapperName + ".insertMusicPerson", newMusicPerson);
        session.commit();
        return;
    }

    @Override
    public void insertSonAlbum(MusicAlbum newMusicAlbum) {
        session.insert("insertSonAlbum", newMusicAlbum);
        session.commit();
        return;
    }
}
