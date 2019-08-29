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
public class Bo6MusicAlbum extends BoFile {
    List<Bo7MusicCd> listCd = new ArrayList<>();
    List<Bo8OtherFile> listOtherFiles = new ArrayList<>();
    Bo5MusicSinger parentSinger = null;

    public static boolean vertifyByName(String pathname) {
        if (pathname.startsWith("【专辑"))
            return true;
        else
            return false;
    }

    public Bo6MusicAlbum(File inputAlbumPath) {
        if (inputAlbumPath.exists() == false) {
            return;
        }
        if (inputAlbumPath.getName().startsWith("【专辑】") == false) {
            return;
        }
        if (inputAlbumPath.isDirectory() == false) {
            return;
        }
        File[] sonPaths = inputAlbumPath.listFiles(onlyDirectory);
        for (File sonPath : sonPaths) {
            if (Bo7MusicCd.vertifyByName(sonPath.getName())) {
                System.out.println("                Cd:" + sonPath.getName());
                listCd.add(new Bo7MusicCd(sonPath));
            }
        }

        calcuQuality();
    }

    public List<Bo7MusicCd> getListCd() {
        return listCd;
    }

    public void setListCd(List<Bo7MusicCd> listCd) {
        this.listCd = listCd;
    }

    public List<Bo8OtherFile> getListOtherFiles() {
        return listOtherFiles;
    }

    public void setListOtherFiles(List<Bo8OtherFile> listOtherFiles) {
        this.listOtherFiles = listOtherFiles;
    }

    public Bo5MusicSinger getParentSinger() {
        return parentSinger;
    }

    public void setParentSinger(Bo5MusicSinger parentSinger) {
        this.parentSinger = parentSinger;
    }

    public void calcuQuality() {

    }

    @Override
    public void calculate() {

    }

    @Override
    public void cycleCalculate() {

    }

    public static Bo6MusicAlbum cycleCreate(Bo5MusicSinger inputParentSinger, File inputPath) {
        Bo6MusicAlbum album = new Bo6MusicAlbum(inputPath);
        album.setParentSinger(inputParentSinger);
        File[] paths = inputPath.listFiles(onlyDirectory);
        for (File path : paths) {
            Bo7MusicCd bo7MusicCd = Bo7MusicCd.cycleCreate(album, path);
            album.getListCd().add(bo7MusicCd);
        }
        File[] files = inputPath.listFiles(onlyFile);
        for (File file : files) {
            Bo8OtherFile bo8OtherFile = new Bo8OtherFile(file);
            album.getListOtherFiles().add(bo8OtherFile);
        }
        return album;
    }
}
