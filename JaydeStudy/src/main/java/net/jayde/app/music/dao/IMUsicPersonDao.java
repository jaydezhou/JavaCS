package net.jayde.app.music.dao;

import net.jayde.app.music.pojo.MusicAlbum;
import net.jayde.app.music.pojo.MusicFavourite;
import net.jayde.app.music.pojo.MusicPerson;

import java.util.List;

public interface IMUsicPersonDao {
    MusicPerson getMusicPerson(String id);

    List<MusicAlbum> getSonAlbums(String pId);
    List<MusicFavourite> getSonFavourites(String pId);
    void insertMusicPerson(MusicPerson newMusicPerson);

    void insertSonAlbum(MusicAlbum newMusicAlbum);
}
