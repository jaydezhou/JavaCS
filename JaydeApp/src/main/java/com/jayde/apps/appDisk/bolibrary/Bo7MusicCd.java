package com.jayde.apps.appDisk.bolibrary;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-16 14:57
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-16 14:57
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Bo7MusicCd extends BoFile {
    String id;
    String name;
    int filesCount;
    long filesSize;
    ArrayList filesList;
    InfoQuality infoQuality;
    List<Bo8MusicFile> listMusicFile = new ArrayList<>();
    List<Bo8MusicInfoFile> listMusicInfoFile = new ArrayList<>();
    List<Bo8OtherFile> listMusicOtherFile = new ArrayList<>();
    Bo6MusicAlbum parentAlbum = null;
    BoMusicQuality quality;

    public static boolean vertifyByName(String pathname) {
        if (pathname.startsWith("【CD"))
            return true;
        else
            return false;
    }

    public Bo7MusicCd(File inputCdPath) {
        setSelfFile(inputCdPath);
        if (inputCdPath.exists() == false) {
            return;
        }
        if (inputCdPath.getName().startsWith("【专辑】") == false) {
            return;
        }
        if (inputCdPath.isDirectory() == false) {
            return;
        }
//        File[] sonFiles = inputCdPath.listFiles(onlyFile);
//        for (File sonFile : sonFiles) {
//            System.out.println("                  File:" + sonFile.getName());
//            if (UtilMusicFile.isMusicFile(sonFile)) {
//                listMusicFile.add(new Bo8MusicFile(sonFile, this));
//            }
//            listMusicOtherFile.add(new Bo8OtherFile(sonFile, this));
//        }
//        File[] sonPaths = inputCdPath.listFiles(onlyDirectory);
//        for (File sonPath : sonPaths) {
//            listMusicOtherFile.add(new Bo8OtherFile(sonPath, this));
//        }
        calcuQuality();
    }

    public List<Bo8MusicFile> getListMusicFile() {
        return listMusicFile;
    }

    public void setListMusicFile(List<Bo8MusicFile> listMusicFile) {
        this.listMusicFile = listMusicFile;
    }

    public List<Bo8MusicInfoFile> getListMusicInfoFile() {
        return listMusicInfoFile;
    }

    public void setListMusicInfoFile(List<Bo8MusicInfoFile> listMusicInfoFile) {
        this.listMusicInfoFile = listMusicInfoFile;
    }

    public List<Bo8OtherFile> getListMusicOtherFile() {
        return listMusicOtherFile;
    }

    public void setListMusicOtherFile(List<Bo8OtherFile> listMusicOtherFile) {
        this.listMusicOtherFile = listMusicOtherFile;
    }

    public Bo6MusicAlbum getParentAlbum() {
        return parentAlbum;
    }

    public void setParentAlbum(Bo6MusicAlbum parentAlbum) {
        this.parentAlbum = parentAlbum;
    }

    public void calcuQuality() {
        int allCount = listMusicFile.size() + listMusicInfoFile.size() + listMusicOtherFile.size();
        float scoreTotal = 0f;
        for (Bo8MusicFile musicFile : listMusicFile) {
//            scoreTotal = scoreTotal+InfoQualityMusic.vertifyMusicFile(file);
        }
    }

    @Override
    public float calculate() {
        return 0f;
    }

    @Override
    public float cycleCalculate() {
        return scoretotal;

    }

    public static Bo7MusicCd cycleCreate(Bo6MusicAlbum inputParentAlbum, File inputPath) {
        Bo7MusicCd cd = new Bo7MusicCd(inputPath);
        cd.setParentAlbum(inputParentAlbum);
        File[] paths = inputPath.listFiles(onlyDirectory);
        for (File path : paths) {
            Bo8OtherFile bo8OtherFile = new Bo8OtherFile(path);
            cd.getListMusicOtherFile().add(bo8OtherFile);
        }
        File[] files = inputPath.listFiles(onlyFile);
        for (File file : files) {
            int filetype = BoFile.getFileType(file);
            if (filetype == BoFile.MUSIC_FILE) {
                Bo8MusicFile musicFile = new Bo8MusicFile(file, cd);
                cd.getListMusicFile().add(musicFile);
            }
            if (filetype == BoFile.Music_INFOFILE) {
                Bo8MusicInfoFile musicInfoFile = new Bo8MusicInfoFile(file, cd);
                cd.getListMusicInfoFile().add(musicInfoFile);
            }
            if (filetype == BoFile.MUSIC_OTHERFILE) {
                Bo8OtherFile otherFile = new Bo8OtherFile(file, cd);
                cd.getListMusicOtherFile().add(otherFile);
            }
//            Bo8OtherFile bo8OtherFile = new Bo8OtherFile(file);
//            album.getListOtherFiles().add(bo8OtherFile);
        }
        return cd;
    }

    @Override
    public void cycleShowTree() {
        System.out.print(BoFile.TREE_BLANK7);
        System.out.println(this);
        for (Bo8MusicFile musicFile : listMusicFile) {
            System.out.print(BoFile.TREE_BLANK8);
            System.out.println(musicFile);
        }
        for (Bo8MusicInfoFile musicInfoFile : listMusicInfoFile) {
            System.out.print(BoFile.TREE_BLANK8);
            System.out.println(musicInfoFile);
        }
        for (Bo8OtherFile otherFile : listMusicOtherFile) {
            System.out.print(BoFile.TREE_BLANK8);
            System.out.println(otherFile);
        }
    }
}

