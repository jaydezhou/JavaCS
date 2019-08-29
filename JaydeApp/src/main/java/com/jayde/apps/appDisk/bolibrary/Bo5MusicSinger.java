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
public class Bo5MusicSinger  extends BoFile{
    static OnlyDirectory onlyDirectory = new OnlyDirectory();
    List<Bo6MusicAlbum> listAlbum = new ArrayList<>();
    Bo4MusicType parentType = null;
    int mediaType = 0;

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

    public void calcuQuality(){

    }

    @Override
    public void calculate() {

    }

    @Override
    public void cycleCalculate() {

    }

    @Override
    public Bo5MusicSinger cycleCreate() {
        return null;
    }
}
