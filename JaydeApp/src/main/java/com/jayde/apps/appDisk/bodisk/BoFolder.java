package com.jayde.apps.appDisk.bodisk;


import org.dom4j.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bodisk
 * @ClassName: ${TYPE_NAME}
 * @Description: 所有物理文件夹的父类
 * @Author: jayde
 * @CreateDate: 2019-09-11 11:25
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-09-11 11:25
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class BoFolder extends AbstractBoFile {
    long selfFilesCount;
    long selfFolderCount;
    long selfFilesSize;
    long allFilesCount;
    long allFolderCount;
    long allFilesSize;
    List<BoFolder> listSonFolder = null;
    List<BoFile> listSonFile = null;
    BoFolder parentFolder = null;

    public long getSelfFilesCount() {
        return selfFilesCount;
    }

    public void setSelfFilesCount(long selfFilesCount) {
        this.selfFilesCount = selfFilesCount;
    }

    public long getSelfFolderCount() {
        return selfFolderCount;
    }

    public void setSelfFolderCount(long selfFolderCount) {
        this.selfFolderCount = selfFolderCount;
    }

    public long getSelfFilesSize() {
        return selfFilesSize;
    }

    public void setSelfFilesSize(long selfFilesSize) {
        this.selfFilesSize = selfFilesSize;
    }

    public long getAllFilesCount() {
        return allFilesCount;
    }

    public void setAllFilesCount(long allFilesCount) {
        this.allFilesCount = allFilesCount;
    }

    public long getAllFolderCount() {
        return allFolderCount;
    }

    public void setAllFolderCount(long allFolderCount) {
        this.allFolderCount = allFolderCount;
    }

    public long getAllFilesSize() {
        return allFilesSize;
    }

    public void setAllFilesSize(long allFilesSize) {
        this.allFilesSize = allFilesSize;
    }

    public List<BoFolder> getListSonFolder() {
        return listSonFolder;
    }

    public void setListSonFolder(List<BoFolder> listSonFolder) {
        this.listSonFolder = listSonFolder;
    }

    public List<BoFile> getListSonFile() {
        return listSonFile;
    }

    public void setListSonFile(List<BoFile> listSonFile) {
        this.listSonFile = listSonFile;
    }


    public BoFolder(String inputFileName) {
        super(inputFileName);
        listSonFolder = new ArrayList<>();
        listSonFile = new ArrayList<>();
        initFileType();
    }

    public BoFolder(File inputFile) {
        super(inputFile);
        listSonFolder = new ArrayList<>();
        listSonFile = new ArrayList<>();
        initFileType();
    }

    public BoFolder(Element inputEle) {
        super(inputEle);
        setSelfFilesCount(Long.parseLong(inputEle.attributeValue("selfFilesCount")));
        setSelfFolderCount(Long.parseLong(inputEle.attributeValue("selfFolderCount")));
        setSelfFilesSize(Long.parseLong(inputEle.attributeValue("selfFilesSize")));
        setAllFilesCount(Long.parseLong(inputEle.attributeValue("allFilesCount")));
        setAllFolderCount(Long.parseLong(inputEle.attributeValue("allFolderCount")));
        setAllFilesSize(Long.parseLong(inputEle.attributeValue("allFilesSize")));
        listSonFolder = new ArrayList<>();
        listSonFile = new ArrayList<>();
        initFileType();
    }

    @Override
    public Element toElement(Element parentELement) {
        Element currentEle = parentELement.addElement("P");
        currentEle.addAttribute("name", filename);
        currentEle.addAttribute("modifyDate", String.valueOf(modifyDate));
        currentEle.addAttribute("attributes", attributes);
        currentEle.addAttribute("filetype", String.valueOf(fileType));
        currentEle.addAttribute("score", String.valueOf(score));
        currentEle.addAttribute("selfFilesCount", String.valueOf(selfFilesCount));
        currentEle.addAttribute("selfFolderCount", String.valueOf(selfFolderCount));
        currentEle.addAttribute("selfFilesSize", String.valueOf(selfFilesSize));
        currentEle.addAttribute("allFilesCount", String.valueOf(allFilesCount));
        currentEle.addAttribute("allFolderCount", String.valueOf(allFolderCount));
        currentEle.addAttribute("allFilesSize", String.valueOf(allFilesSize));
        currentEle.addAttribute("id", id);
        currentEle.addAttribute("pid", pid);
//        currentEle.addAttribute("suffix", suffix);
        for (BoFile boFile : listSonFile) {
            if (boFile instanceof BoMusicSongFile) {
                BoMusicSongFile boMusicSongFile = (BoMusicSongFile) boFile;
                currentEle = boMusicSongFile.toElement(currentEle);
            } else {
                currentEle = boFile.toElement(currentEle);
            }
        }
        for (BoFolder boFolder : listSonFolder) {
            currentEle = boFolder.toElement(currentEle);
        }
        return parentELement;
    }
    private void initFileType() {
        setFileType(AbstractBoFile.FILETYPE_FOLDER_BOFOLDER);
    }
    @Override
    public String toString() {
        return "P:" + filename;
    }
}
