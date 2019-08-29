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
public class Bo4MusicType  extends BoFile{
    static OnlyDirectory onlyDirectory = new OnlyDirectory();
    List<Bo5MusicSinger> listSinger = new ArrayList<>();
    Bo3MusicGroup parentGroup = null;
    int mediaType = 0;

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

    public void calcuQuality(){

    }
}
