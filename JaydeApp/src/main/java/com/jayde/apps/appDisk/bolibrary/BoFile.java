package com.jayde.apps.appDisk.bolibrary;

import java.io.File;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-28 16:49
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-28 16:49
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public abstract class BoFile implements BoFileInterface {
    public static int MUSIC_LIBRARYSET = 1;
    public static int MUSIC_LIBRARY = 2;
    public static int MUSIC_TYPE = 3;
    public static int MUSIC_GROUP = 4;
    public static int MUSIC_SINGER = 5;
    public static int MUSIC_ALBUM = 6;
    public static int MUSIC_CD = 7;
    public static int MUSIC_FILE = 8;
    public static int Music_INFOFILE = 9;
    public static int MUSIC_OTHERFILE = 10;

    public static int MUSIC_MEDIA_MP3 = 1;
    public static int MUSIC_MEDIA_HIFI = 2;
    public static int MUSIC_MEDIA_51 = 3;
    public static int MUSIC_MEDIA_DSD = 4;

    File selfFile;
    int type;
    float scoretotal;
    boolean calculated = false;

    public BoFile() {
        System.out.println("blank constract");
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getScoretotal() {
        return scoretotal;
    }

    public void setScoretotal(float scoretotal) {
        this.scoretotal = scoretotal;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public void setCalculated(boolean calculated) {
        this.calculated = calculated;
    }

    public File getSelfFile() {
        return selfFile;
    }

    public void setSelfFile(File selfFile) {
        this.selfFile = selfFile;
    }

}
