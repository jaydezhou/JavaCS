package net.jayde.app.music.dao;

import net.jayde.app.music.pojo.*;

import java.util.List;

public abstract class MusicDao implements IMusicDao {
    protected IMusicGroupDao iMusicGroupDao = null;
    protected IMUsicPersonDao iMusicPersonDao = null;

    public IMusicGroupDao getiMusicGroupDao() {
        return iMusicGroupDao;
    }

    public void setiMusicGroupDao(IMusicGroupDao iMusicGroupDao) {
        this.iMusicGroupDao = iMusicGroupDao;
    }

    public IMUsicPersonDao getiMusicPersonDao() {
        return iMusicPersonDao;
    }

    public void setiMusicPersonDao(IMUsicPersonDao iMusicPersonDao) {
        this.iMusicPersonDao = iMusicPersonDao;
    }

    public MusicGroup getRootMusicGroup() {
        return iMusicGroupDao.getRootMusicGroup();
    }

    public MusicGroup getMusicGroup(String id) {
        return iMusicGroupDao.getMusicGroup(id);
    }

    public List<MusicGroup> getSonMusicGroups(String preId) {
        return iMusicGroupDao.getSonMusicGroups(preId);
    }

    public List<MusicPerson> getSonMusicPersons(String gId) {
        return iMusicGroupDao.getSonMusicPersons(gId);
    }

    public MusicPerson getMusicPerson(String id) {
        return iMusicPersonDao.getMusicPerson(id);
    }

    public List<MusicAlbum> getSonAlbums(String pId) {
        return iMusicPersonDao.getSonAlbums(pId);
    }

    public List<MusicFavourite> getSonFavourites(String pId) {
        return iMusicPersonDao.getSonFavourites(pId);
    }

    public abstract MusicLibrary iniMusicLibrary();

    public void insertMusicPerson(MusicPerson newMusicPerson) {
         iMusicPersonDao.insertMusicPerson(newMusicPerson);
         return;
    }
    public void insertSonAlbum(MusicAlbum newMusicAlbum){
        iMusicPersonDao.insertSonAlbum(newMusicAlbum);
        return;
    };
}
