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
public class Bo6MusicAlbum  extends BoFile{
    static OnlyDirectory onlyDirectory = new OnlyDirectory();
    List<Bo7MusicCd> listCd = new ArrayList<>();
    Bo5MusicSinger parentSinger = null;
    int mediaType = 0;

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

    public void calcuQuality(){

    }

    @Override
    public void calculate() {

    }

    @Override
    public void cycleCalculate() {

    }

    @Override
    public Bo6MusicAlbum cycleCreate() {
        return null;
    }
}
