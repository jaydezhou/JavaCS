package net.jayde.app.music.dao;

public interface IMusicDao {
    IMusicGroupDao iMusicGroupDao=null;
    IMUsicPersonDao iMusicPersonDao = null;

    IMusicGroupDao getiMusicGroupDao();

    void setiMusicGroupDao(IMusicGroupDao iMusicGroupDao);

    IMUsicPersonDao getiMusicPersonDao();

    void setiMusicPersonDao(IMUsicPersonDao iMusicPersonDao);


}
