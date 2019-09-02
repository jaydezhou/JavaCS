package com.jayde.apps.appDisk.bolibrary;

import com.jayde.util.diskutils.OnlyDirectory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-13 11:01
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-13 11:01
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Bo3MusicGroup extends BoFile {

    List<Bo4MusicType> listType = new ArrayList<>();
    List<Bo8OtherFile> listOtherFiles = new ArrayList<>();
    Bo2MusicLibrary parentLibrary = null;

    public static boolean vertifyByName(String pathname) {
        if (pathname.startsWith("【组】"))
            return true;
        else
            return false;
    }

    public Bo3MusicGroup(File inputGroupPath) {
        setSelfFile(inputGroupPath);
        if (inputGroupPath.exists() == false) {
            return;
        }
        if (inputGroupPath.getName().startsWith("【组】") == false) {
            return;
        }
        if (inputGroupPath.isDirectory() == false) {
            return;
        }
//        File[] sonPaths = inputGroupPath.listFiles(onlyDirectory);
//        for (File sonPath : sonPaths) {
//            if (Bo4MusicType.vertifyByName(sonPath.getName())) {
//                System.out.println("        Type:" + sonPath.getName());
//                listType.add(new Bo4MusicType(sonPath));
//            }
//        }
        calcuQuality();
    }

    public List<Bo4MusicType> getListType() {
        return listType;
    }

    public void setListType(List<Bo4MusicType> listType) {
        this.listType = listType;
    }

    public List<Bo8OtherFile> getListOtherFiles() {
        return listOtherFiles;
    }

    public void setListOtherFiles(List<Bo8OtherFile> listOtherFiles) {
        this.listOtherFiles = listOtherFiles;
    }

    public Bo2MusicLibrary getParentLibrary() {
        return parentLibrary;
    }

    public void setParentLibrary(Bo2MusicLibrary parentLibrary) {
        this.parentLibrary = parentLibrary;
    }

    public void calcuQuality() {

    }

    @Override
    public float calculate() {
        return 0f;
    }

    @Override
    public float cycleCalculate() {
        int allCount = listType.size() + listOtherFiles.size() + 1;
        for (Bo4MusicType type: listType) {
            scoretotal = scoretotal + type.cycleCalculate();
        }
        for (Bo8OtherFile otherFile : listOtherFiles) {
            scoretotal = scoretotal + 0f;
        }
        //自身的名字分数
        if (selfFile.getName().startsWith("【组】")) {
            scoretotal = scoretotal + 100f;
        }
        scoretotal = scoretotal / allCount;
        return scoretotal;

    }

    public static Bo3MusicGroup cycleCreate(Bo2MusicLibrary inputPparentLibrary, File inputPath) {
        Bo3MusicGroup group = new Bo3MusicGroup(inputPath);
        group.setParentLibrary(inputPparentLibrary);
        File[] paths = inputPath.listFiles(onlyDirectory);
        for (File path : paths) {
            Bo4MusicType bo4MusicType = Bo4MusicType.cycleCreate(group, path);
            group.getListType().add(bo4MusicType);
        }
        File[] files = inputPath.listFiles(onlyFile);
        for (File file : files) {
            if (BoFile.getFileType(file) != BoFile.MUSIC_OSFILE) {
                Bo8OtherFile bo8OtherFile = new Bo8OtherFile(file);
                group.getListOtherFiles().add(bo8OtherFile);
            }
        }
        return group;
    }

    @Override
    public void cycleShowTree() {
        System.out.print(BoFile.TREE_BLANK3);
        System.out.println(this);
        for (Bo4MusicType type : listType) {
            type.cycleShowTree();
        }
        for (Bo8OtherFile otherFile : listOtherFiles) {
            System.out.print(BoFile.TREE_BLANK4);
            System.out.println(otherFile);
        }
    }
}


