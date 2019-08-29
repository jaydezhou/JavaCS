package com.jayde.apps.appDisk.bolibrary;

import java.io.File;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-13 11:12
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-13 11:12
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class UtilLibrary {
    /*
       Bo1LibrarySet
         Bo2MusicLibrary
           Bo4MusicType
             Bo3MusicGroup
               Bo5MusicSinger
                 Bo6MusicAlbum
                   Bo8MusicFile
                   Bo8OtherFile
        */
    public void createLibrarySet(File rootPath) {

    }

    public void updateLibrarySet(File rootPath) {

    }

    public void createMusicLibrary(Bo1LibrarySet librarySet, File musicLibraryPath) {

    }

    public void createMusicType(Bo2MusicLibrary musicLibrary, File musicTypePath) {

    }

    public void createMusicGroup(Bo4MusicType musicType, File musicGroupPath) {

    }

    public void createMusicSinger(Bo3MusicGroup musicGroup, File musicSingerPath) {

    }

    public void craeteMusicAlbum(Bo5MusicSinger musicSinger, File musicAlbumPath) {

    }

    public void createMusicFile(Bo6MusicAlbum musicAlbum, File musicFile) {

    }

    public InfoQuality qualityMusicFile(File file) {
        InfoQuality infoQuality;
        return null;

    }

}
