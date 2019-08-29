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
 * @CreateDate: 2019-08-13 11:09
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-13 11:09
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Bo5MusicSinger extends BoFile {
    List<Bo6MusicAlbum> listAlbum = new ArrayList<>();
    List<Bo8OtherFile> listOtherFiles = new ArrayList<>();
    Bo4MusicType parentType = null;

    public static boolean vertifyByName(String pathname) {
        if (pathname.startsWith("【歌手】"))
            return true;
        else
            return false;
    }

    public Bo5MusicSinger(File inputSingerPath) {
        if (inputSingerPath.exists() == false) {
            return;
        }
        if (inputSingerPath.getName().startsWith("【歌手】") == false) {
            return;
        }
        if (inputSingerPath.isDirectory() == false) {
            return;
        }
        File[] sonPaths = inputSingerPath.listFiles(onlyDirectory);
        for (File sonPath : sonPaths) {
            if (Bo6MusicAlbum.vertifyByName(sonPath.getName())) {
                System.out.println("              Album:" + sonPath.getName());
                listAlbum.add(new Bo6MusicAlbum(sonPath));
            }
        }

        calcuQuality();
    }

    public List<Bo6MusicAlbum> getListAlbum() {
        return listAlbum;
    }

    public void setListAlbum(List<Bo6MusicAlbum> listAlbum) {
        this.listAlbum = listAlbum;
    }

    public List<Bo8OtherFile> getListOtherFiles() {
        return listOtherFiles;
    }

    public void setListOtherFiles(List<Bo8OtherFile> listOtherFiles) {
        this.listOtherFiles = listOtherFiles;
    }

    public Bo4MusicType getParentType() {
        return parentType;
    }

    public void setParentType(Bo4MusicType parentType) {
        this.parentType = parentType;
    }

    public void calcuQuality() {

    }

    @Override
    public void calculate() {

    }

    @Override
    public void cycleCalculate() {

    }

    public static Bo5MusicSinger cycleCreate(Bo4MusicType inputParentType, File inputPath) {
        Bo5MusicSinger singer = new Bo5MusicSinger(inputPath);
        singer.setParentType(inputParentType);
        File[] paths = inputPath.listFiles(onlyDirectory);
        for (File path : paths) {
            Bo6MusicAlbum bo6MusicAlbum = Bo6MusicAlbum.cycleCreate(singer, path);
            singer.getListAlbum().add(bo6MusicAlbum);
        }
        File[] files = inputPath.listFiles(onlyFile);
        for (File file : files) {
            Bo8OtherFile bo8OtherFile = new Bo8OtherFile(file);
            singer.getListOtherFiles().add(bo8OtherFile);
        }
        return singer;
    }
}
