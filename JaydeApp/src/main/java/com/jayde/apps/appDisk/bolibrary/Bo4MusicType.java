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
public class Bo4MusicType extends BoFile {
    List<Bo5MusicSinger> listSinger = new ArrayList<>();
    List<Bo8OtherFile> listOtherFiles = new ArrayList<>();
    Bo3MusicGroup parentGroup = null;

    public static boolean vertifyByName(String pathname) {
        if (pathname.startsWith("【类】"))
            return true;
        else
            return false;
    }

    public Bo4MusicType(File inputTypepPath) {
        if (inputTypepPath.exists() == false) {
            return;
        }
        if (inputTypepPath.getName().startsWith("【类】") == false) {
            return;
        }
        if (inputTypepPath.isDirectory() == false) {
            return;
        }
        File[] sonPaths = inputTypepPath.listFiles(onlyDirectory);
        for (File sonPath : sonPaths) {
            if (Bo5MusicSinger.vertifyByName(sonPath.getName())) {
                System.out.println("          Singer:" + sonPath.getName());
                listSinger.add(new Bo5MusicSinger(sonPath));
            }
        }


        calcuQuality();
    }

    public List<Bo5MusicSinger> getListSinger() {
        return listSinger;
    }

    public void setListSinger(List<Bo5MusicSinger> listSinger) {
        this.listSinger = listSinger;
    }

    public List<Bo8OtherFile> getListOtherFiles() {
        return listOtherFiles;
    }

    public void setListOtherFiles(List<Bo8OtherFile> listOtherFiles) {
        this.listOtherFiles = listOtherFiles;
    }

    public Bo3MusicGroup getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(Bo3MusicGroup parentGroup) {
        this.parentGroup = parentGroup;
    }

    public void calcuQuality() {

    }

    @Override
    public void calculate() {

    }

    @Override
    public void cycleCalculate() {

    }

    public static Bo4MusicType cycleCreate(Bo3MusicGroup inputParentGroup, File inputPath) {
        Bo4MusicType type = new Bo4MusicType(inputPath);
        type.setParentGroup(inputParentGroup);
        File[] paths = inputPath.listFiles(onlyDirectory);
        for (File path : paths) {
            Bo5MusicSinger bo5MusicSinger = Bo5MusicSinger.cycleCreate(type, path);
            type.getListSinger().add(bo5MusicSinger);
        }
        File[] files = inputPath.listFiles(onlyFile);
        for (File file : files) {
            Bo8OtherFile bo8OtherFile = new Bo8OtherFile(file);
            type.getListOtherFiles().add(bo8OtherFile);
        }
        return type;
    }
}
