package com.jayde.apps.appDisk.bodisk;


import org.dom4j.Element;

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
        suffix = UtilBoFile.getUpperSuffix(filename);
        size = 0;
        initFileType();
    }

    public BoFile(File inputFile) {
        super(inputFile);
        suffix = UtilBoFile.getUpperSuffix(filename);
        size = inputFile.length();
        initFileType();
    }

    public BoFile(Element inputEle) {
        super(inputEle);
        suffix = UtilBoFile.getUpperSuffix(filename);
        size = Long.parseLong(inputEle.attributeValue("size"));
    }

    private void initFileType() {
        setFileType(AbstractBoFile.FILETYPE_FILE_BOFILE);
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
                setFileType(AbstractBoFile.FILETYPE_FILE_BOMUSICSONGFILE);
                break;
        }
    }

    @Override
    public Element toElement(Element parentELement) {
        Element currentEle = parentELement.addElement("F");
        currentEle.addAttribute("name", filename);
        currentEle.addAttribute("size", String.valueOf(size));
        currentEle.addAttribute("modifyDate", String.valueOf(modifyDate));
        currentEle.addAttribute("attributes", attributes);
        currentEle.addAttribute("filetype", String.valueOf(fileType));
        currentEle.addAttribute("score", String.valueOf(score));
//        currentEle.addAttribute("suffix", suffix);
        currentEle.addAttribute("id", id);
        currentEle.addAttribute("pid", pid);
        return parentELement;
    }

    @Override
    public String toString() {
        return "F:" + filename;
    }
}
