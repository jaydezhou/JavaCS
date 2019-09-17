package com.jayde.apps.appDisk.bodisk;


import java.io.File;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bodisk
 * @ClassName: ${TYPE_NAME}
 * @Description: 所有物理文件的父类
 * @Author: jayde
 * @CreateDate: 2019-09-11 11:25
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-09-11 11:25
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class BoFile extends AbstractBoFile {
    final static int FILE_TYPE_11_MUSIC_SONG = 11;

    String suffix;
    long size;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public BoFile(String inputFileName) {
        super(inputFileName);
        suffix = UtilBoFile.getUpperSuffix(inputFileName);
        size = 0;
        initFileType();
    }

    public BoFile(File inputFile) {
        super(inputFile);
        suffix = UtilBoFile.getUpperSuffix(inputFile.getName());
        size = inputFile.length();
        initFileType();
    }

    private void initFileType() {
        switch (suffix) {
            case "AAC":
            case "ACC":
            case "ALAC":
            case "APE":
            case "DIFF":
            case "DSF":
            case "DST":
            case "DTS":
            case "FLAC":
            case "MP3":
            case "OGG":
            case "WAV":
                setFileType(FILE_TYPE_11_MUSIC_SONG);
                break;
        }
    }

    @Override
    public String toString() {
        return "BoFile{" +
                "filename='" + filename + '\'' +
                '}';
    }
}
