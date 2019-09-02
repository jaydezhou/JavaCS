package com.jayde.apps.appDisk.bolibrary;

import java.io.File;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-28 16:41
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-28 16:41
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class UtilMusic {

//    static UtilVertifyName utilVertifyName = new UtilVertifyName();
//    static UtilVertifySize utilVertifySize;
//    static UtilVertifyEncode utilVertifyEncode;
//    static UtilVertifyHeader utilVertifyHeader;
//    static UtilVertifyTag utilVertifyTag;

    public static float cycleCalculate(Bo1LibrarySet librarySet) {
        float scoreTotal = 0f;
        //name
        scoreTotal = scoreTotal + UtilVertifyName.vertifyName(librarySet, BoFile.MUSIC_LIBRARYSET);
        for (Bo2MusicLibrary library : librarySet.getListLibrary()) {
            scoreTotal = scoreTotal + cycleCalculate(library);
        }
        for (Bo8OtherFile otherFile : librarySet.getListOtherFiles()) {
            scoreTotal = scoreTotal + 0f;
        }
        int allCount = librarySet.getListLibrary().size() + librarySet.getListOtherFiles().size() + 1;
        scoreTotal = scoreTotal / allCount;
        librarySet.setScoretotal(scoreTotal);
        return scoreTotal;
    }

    public static float cycleCalculate(Bo2MusicLibrary library) {
        float scoreTotal = 0f;
        //name
        scoreTotal = scoreTotal + UtilVertifyName.vertifyName(library, BoFile.MUSIC_LIBRARY);
        for (Bo3MusicGroup group : library.listGroup) {
            scoreTotal = scoreTotal + cycleCalculate(group);
        }
        for (Bo8OtherFile otherFile : library.getListOtherFiles()) {
            scoreTotal = scoreTotal + 0f;
        }
        int allCount = library.getListGroup().size() + library.getListOtherFiles().size() + 1;
        scoreTotal = scoreTotal / allCount;
        library.setScoretotal(scoreTotal);
        return scoreTotal;
    }

    public static float cycleCalculate(Bo3MusicGroup group) {
        float scoreTotal = 0f;
        //name
        scoreTotal = scoreTotal + UtilVertifyName.vertifyName(group, BoFile.MUSIC_GROUP);
        for (Bo4MusicType type : group.listType) {
            scoreTotal = scoreTotal + cycleCalculate(type);
        }
        for (Bo8OtherFile otherFile : group.getListOtherFiles()) {
            scoreTotal = scoreTotal + 0f;
        }
        int allCount = group.getListType().size() + group.getListOtherFiles().size() + 1;
        scoreTotal = scoreTotal / allCount;
        group.setScoretotal(scoreTotal);
        return scoreTotal;
    }

    public static float cycleCalculate(Bo4MusicType type) {
        float scoreTotal = 0f;
        //name
        scoreTotal = scoreTotal + UtilVertifyName.vertifyName(type, BoFile.MUSIC_TYPE);
        for (Bo5MusicSinger singer : type.getListSinger()) {
            scoreTotal = scoreTotal + cycleCalculate(singer);
        }
        for (Bo8OtherFile otherFile : type.getListOtherFiles()) {
            scoreTotal = scoreTotal + 0f;
        }
        int allCount = type.getListSinger().size() + type.getListOtherFiles().size() + 1;
        scoreTotal = scoreTotal / allCount;
        type.setScoretotal(scoreTotal);
        return scoreTotal;
    }

    public static float cycleCalculate(Bo5MusicSinger singer) {
        float scoreTotal = 0f;
        //name
        scoreTotal = scoreTotal + UtilVertifyName.vertifyName(singer, BoFile.MUSIC_SINGER);
        for (Bo6MusicAlbum album : singer.getListAlbum()) {
            scoreTotal = scoreTotal + cycleCalculate(album);
        }
        for (Bo8OtherFile otherFile : singer.getListOtherFiles()) {
            scoreTotal = scoreTotal + 0f;
        }
        int allCount = singer.getListAlbum().size() + singer.getListOtherFiles().size() + 1;
        scoreTotal = scoreTotal / allCount;
        singer.setScoretotal(scoreTotal);
        return scoreTotal;
    }

    public static float cycleCalculate(Bo6MusicAlbum album) {
        float scoreTotal = 0f;
        //name
        scoreTotal = scoreTotal + UtilVertifyName.vertifyName(album, BoFile.MUSIC_ALBUM);
        for (Bo7MusicCd cd : album.getListCd()) {
            scoreTotal = scoreTotal + cycleCalculate(cd);
        }
        for (Bo8OtherFile otherFile : album.getListOtherFiles()) {
            scoreTotal = scoreTotal + 0f;
        }
        int allCount = album.getListCd().size() + album.getListOtherFiles().size() + 1;
        scoreTotal = scoreTotal / allCount;
        album.setScoretotal(scoreTotal);
        return scoreTotal;
    }

    public static float cycleCalculate(Bo7MusicCd cd) {
        float scoreTotal = 0f;
        //name
        scoreTotal = scoreTotal + UtilVertifyName.vertifyName(cd, BoFile.MUSIC_CD);
        for (Bo8MusicFile musicFile : cd.getListMusicFile()) {
            scoreTotal = scoreTotal + cycleCalculate(musicFile);
        }
        for (Bo8MusicInfoFile musicInfoFile : cd.getListMusicInfoFile()) {
            scoreTotal = scoreTotal + cycleCalculate(musicInfoFile);
        }
        for (Bo8OtherFile otherFile : cd.getListMusicOtherFile()) {
            scoreTotal = scoreTotal + cycleCalculate(otherFile);
        }
        int allCount = cd.getListMusicFile().size() + cd.getListMusicInfoFile().size() + cd.getListMusicOtherFile().size() + 1;
        scoreTotal = scoreTotal / allCount;
        cd.setScoretotal(scoreTotal);
        return scoreTotal;
    }

    public static float cycleCalculate(Bo8MusicFile musicFile) {
        float scoreTotal = 0f;
        //name
        scoreTotal = scoreTotal + UtilVertifyName.vertifyName(musicFile, BoFile.MUSIC_FILE);
        scoreTotal = scoreTotal + UtilVertifySize.vertifySize(musicFile, BoFile.MUSIC_FILE);
        scoreTotal = scoreTotal/2;
        musicFile.setScoretotal(scoreTotal);
        return scoreTotal;
    }

    public static float cycleCalculate(Bo8MusicInfoFile musicInfoFile) {
        float scoreTotal = 0f;
        //name
        scoreTotal = scoreTotal + UtilVertifyName.vertifyName(musicInfoFile, BoFile.Music_INFOFILE);
        scoreTotal = scoreTotal + UtilVertifySize.vertifySize(musicInfoFile, BoFile.MUSIC_FILE);
        scoreTotal = scoreTotal/2;
        musicInfoFile.setScoretotal(scoreTotal);
        return scoreTotal;
    }

    public static float cycleCalculate(Bo8OtherFile otherFile) {
        float scoreTotal = 0f;
        //name
        scoreTotal = scoreTotal + UtilVertifyName.vertifyName(otherFile, BoFile.MUSIC_OTHERFILE);
        scoreTotal = scoreTotal + UtilVertifySize.vertifySize(otherFile, BoFile.MUSIC_FILE);
        scoreTotal = scoreTotal/2;
        otherFile.setScoretotal(scoreTotal);
        return scoreTotal;
    }

}

class UtilVertifyName {
    public static float vertifyName(BoFile boFile, int type) {
        String filename = boFile.getSelfFile().getName();
        float scoreName = 0f;
        switch (type) {
            case BoFile.MUSIC_LIBRARYSET: {
                if (filename.equals("标准文件夹")) {
                    scoreName = 100f;
                }
                break;
            }
            case BoFile.MUSIC_LIBRARY: {
                if (filename.equals("【音频】")) {
                    scoreName = 100f;
                }
                break;
            }
            case BoFile.MUSIC_GROUP: {
                if (filename.startsWith("【组】")) {
                    scoreName = 50f;
                }
                if (UtilCommon.isSimple(filename)) {
                    scoreName = scoreName + 50f;
                }
                break;
            }
            case BoFile.MUSIC_TYPE: {
                if (filename.startsWith("【类】")) {
                    scoreName = 50f;
                }
                if (UtilCommon.isSimple(filename)) {
                    scoreName = scoreName + 50f;
                }
                break;
            }
            case BoFile.MUSIC_SINGER: {
                if (filename.startsWith("【歌手】")) {
                    scoreName = 50f;
                }
                if (UtilCommon.isSimple(filename)) {
                    scoreName = scoreName + 50f;
                }
                break;
            }
            case BoFile.MUSIC_ALBUM: {
                if (UtilCommon.isSimple(filename)) {
                    scoreName = 100f;
                }
                break;
            }
            case BoFile.MUSIC_CD: {
                if (UtilCommon.isSimple(filename)) {
                    scoreName = 100f;
                }
                break;
            }
            case BoFile.MUSIC_FILE: {
                if (UtilCommon.isSimple(filename)) {
                    scoreName = 100f;
                }
                break;
            }
            case BoFile.Music_INFOFILE: {
                if (UtilCommon.isSimple(filename)) {
                    scoreName = 100f;
                }
                break;
            }
            case BoFile.MUSIC_OTHERFILE: {
                if (UtilCommon.isSimple(filename)) {
                    scoreName = 100f;
                }
                break;
            }
            default: {

            }
        }
        return scoreName;
    }
}

class UtilVertifySize {
    public static float vertifySize(BoFile boFile, int type) {
        float scoreSize = 100f;
        if (boFile.getSelfFile().isFile() && boFile.getSelfFile().length() == 0) {
            scoreSize = 0f;
        }
        return scoreSize;
    }
}

class UtilVertifyEncode {
    public static float vertifyEncode(BoFile boFile, int type) {
        return 0f;
    }
}

class UtilVertifyHeader {
    public static float vertifyHeader(BoFile boFile, int type) {
        return 0f;
    }
}

class UtilVertifyTag {
    public static float vertifyTag(BoFile boFile, int type) {
        return 0f;
    }
}

class UtilCommon {
    //只要有繁体，就判定为繁体。因为有些字，简体和繁体是一样的。比如“定影”。如果是这样的字，优先判定为简体。
    public static boolean isSimple(String str) {
        try {
            if (str.equals(new String(str.getBytes("GB2312"), "GB2312"))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}