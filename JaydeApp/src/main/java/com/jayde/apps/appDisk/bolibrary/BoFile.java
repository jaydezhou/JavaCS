package com.jayde.apps.appDisk.bolibrary;

import com.jayde.util.diskutils.OnlyDirectory;
import com.jayde.util.diskutils.OnlyFile;

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
    public static final int MUSIC_LIBRARYSET = 1;
    public static final int MUSIC_LIBRARY = 2;
    public static final int MUSIC_TYPE = 3;
    public static final int MUSIC_GROUP = 4;
    public static final int MUSIC_SINGER = 5;
    public static final int MUSIC_ALBUM = 6;
    public static final int MUSIC_CD = 7;
    public static final int MUSIC_FILE = 8;
    public static final int Music_INFOFILE = 9;
    public static final int MUSIC_OTHERFILE = 10;
    public static final int MUSIC_OSFILE = 11;

    public static final int MUSIC_MEDIA_MP3 = 1;
    public static final int MUSIC_MEDIA_HIFI = 2;
    public static final int MUSIC_MEDIA_51 = 3;
    public static final int MUSIC_MEDIA_DSD = 4;
    public static final String TREE_BLANK1 = "    ";
    public static final String TREE_BLANK2 = "        ";
    public static final String TREE_BLANK3 = "            ";
    public static final String TREE_BLANK4 = "                ";
    public static final String TREE_BLANK5 = "                    ";
    public static final String TREE_BLANK6 = "                        ";
    public static final String TREE_BLANK7 = "                            ";
    public static final String TREE_BLANK8 = "                                ";

    public static final String[] MUSIC_FILE_TYPES = {"MP3", "FLAC", "DTS", "DFF", "WAV", "APE", "M4A", "ACC"};
    public static final String[] MUSIC_LYRICS_TYPES = {"LRC", "SRT", "UTF", "KSC", "SSA", "ASS", "SMI"};

    File selfFile;
    int type;
    float scoretotal;
    boolean calculated = false;

    static OnlyDirectory onlyDirectory = new OnlyDirectory();
    static OnlyFile onlyFile = new OnlyFile();

    public BoFile() {
//        System.out.println("blank constract");
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getScoretotal() {
        scoretotal = 0f;
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

    public static int getFileType(File inputFile) {
        if (inputFile.isDirectory()) {
            return BoFile.MUSIC_OTHERFILE;
        }
        if (inputFile.getName().equals(".DS_Store")) {
            return BoFile.MUSIC_OSFILE;
        }
        String suffix = getFileSuffix(inputFile);
        switch (suffix) {
            case "FLAC": {
                return BoFile.MUSIC_FILE;
            }
            case "MP3": {
                return BoFile.MUSIC_FILE;
            }
            case "DTS": {
                return BoFile.MUSIC_FILE;
            }
            case "DFF": {
                return BoFile.MUSIC_FILE;
            }
            case "WAV": {
                return BoFile.MUSIC_FILE;
            }
            case "APE": {
                return BoFile.MUSIC_FILE;
            }
            case "M4A": {
                return BoFile.MUSIC_FILE;
            }
            case "AAC": {
                return BoFile.MUSIC_FILE;
            }
            case "ACC": {
                return BoFile.MUSIC_FILE;
            }
            case "CUE": {
                return BoFile.Music_INFOFILE;
            }
            case "LRC": {
                return BoFile.Music_INFOFILE;
            }
            case "SRT": {
                return BoFile.Music_INFOFILE;
            }
            case "UTF": {
                return BoFile.Music_INFOFILE;
            }
            case "KSC": {
                return BoFile.Music_INFOFILE;
            }
            case "SSA": {
                return BoFile.Music_INFOFILE;
            }
            case "ASS": {
                return BoFile.Music_INFOFILE;
            }
            case "SMI": {
                return BoFile.Music_INFOFILE;
            }
            case "JPG": {
                return BoFile.Music_INFOFILE;
            }
            case "JPEG": {
                return BoFile.Music_INFOFILE;
            }
            case "BMP": {
                return BoFile.Music_INFOFILE;
            }
            case "GIF": {
                return BoFile.Music_INFOFILE;
            }
            case "PNG": {
                return BoFile.Music_INFOFILE;
            }
            default: {
                return BoFile.MUSIC_OTHERFILE;
            }
        }
    }

    public static String getFileSuffix(File file) {
        String filename = file.getName();
        if (filename.contains(".") == false) {
            return "";
        }
        String[] subs = filename.split("\\.");
        return subs[subs.length - 1].toUpperCase();
    }

    @Override
    public String toString() {
        return selfFile.getName() + "  " + scoretotal;
    }
}
