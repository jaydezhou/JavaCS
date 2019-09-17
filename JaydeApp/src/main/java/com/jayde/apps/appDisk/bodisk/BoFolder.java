package com.jayde.apps.appDisk.bodisk;


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
    }

    public BoFolder(File inputFile) {
        super(inputFile);
        listSonFolder = new ArrayList<>();
        listSonFile = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "BoFolder{" +
                "filename='" + filename + '\'' +
                '}';
    }
}
