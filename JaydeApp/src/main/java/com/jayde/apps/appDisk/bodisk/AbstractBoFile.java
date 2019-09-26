package com.jayde.apps.appDisk.bodisk;

import org.dom4j.Element;

import java.io.File;
import java.util.UUID;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bodisk
 * @ClassName: ${TYPE_NAME}
 * @Description: 所有物理文件（文件夹）的父类
 * @Author: jayde
 * @CreateDate: 2019-09-11 11:34
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-09-11 11:34
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class AbstractBoFile {
    public static final int FILETYPE_ABSTRACT = 0;
    public static final int FILETYPE_FOLDER_BOFOLDER= 10;
    public static final int FILETYPE_FOLDER_BOSETFOLDER= 20;
    public static final int FILETYPE_FOLDER_BONORMALFOLDER= 30;
    public static final int FILETYPE_FILE_BOFILE= 1000;
    public static final int FILETYPE_FILE_BOSETFILE= 1010;
    public static final int FILETYPE_FILE_BONORMALFILE= 1030;
    public static final int FILETYPE_FILE_BOMUSICINFOFILE= 3010;
    public static final int FILETYPE_FILE_BOMUSICOTHERFILE= 3020;
    public static final int FILETYPE_FILE_BOMUSICSONGFILE= 11;


    String id;
    String pid;
    String filename;
    long modifyDate;
    String attributes;
    int fileType = FILETYPE_ABSTRACT;
    float score;
    BoFolder parentFolder;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public BoFolder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(BoFolder parentFolder) {
        this.parentFolder = parentFolder;
        this.pid = parentFolder.getId();
    }

    public AbstractBoFile(File inputFile) {
        this(inputFile.getName());
        modifyDate = inputFile.lastModified();
        StringBuilder stringBuilder = new StringBuilder();
        if (inputFile.canRead()) {
            stringBuilder.append("R");
        } else {
            stringBuilder.append("r");
        }
        if (inputFile.canWrite()) {
            stringBuilder.append("W");
        } else {
            stringBuilder.append("w");
        }
        if (inputFile.canExecute()) {
            stringBuilder.append("E");
        } else {
            stringBuilder.append("e");
        }
        if (inputFile.isHidden() == false) {
            stringBuilder.append("V");
        } else {
            stringBuilder.append("v");
        }
        attributes = stringBuilder.toString();

    }

    public AbstractBoFile(Element inputEle) {
        setId(inputEle.attributeValue("id"));
//        setPid(inputEle.attributeValue("pid"));
        setFilename(inputEle.attributeValue("name"));
        setModifyDate(Long.parseLong(inputEle.attributeValue("modifyDate")));
        setAttributes(inputEle.attributeValue("attributes"));
        setFileType(Integer.parseInt(inputEle.attributeValue("filetype")));
        setScore(Float.parseFloat(inputEle.attributeValue("score")));
    }

    public Element toElement(Element parentELement) {
        return null;
    }

    public AbstractBoFile(String inputFileName) {
        id = UUID.randomUUID().toString();
        pid = "";
        filename = inputFileName;
        modifyDate = 0l;
        attributes = "";
        fileType = 0;
        score = 0f;
    }
}
