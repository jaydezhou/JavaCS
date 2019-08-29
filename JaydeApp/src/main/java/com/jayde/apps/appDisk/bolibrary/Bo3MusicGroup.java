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
public class Bo3MusicGroup  extends BoFile{
    static OnlyDirectory onlyDirectory = new OnlyDirectory();

    List<Bo4MusicType> listType = new ArrayList<>();
    Bo2MusicLibrary parentLibrary = null;
    int mediaType = 0;

    public static boolean vertifyByName(String pathname) {
        if (pathname.startsWith("【组】"))
            return true;
        else
            return false;
    }

    public Bo3MusicGroup(File inputGroupPath) {
        if (inputGroupPath.exists() == false) {
            return;
        }
        if (inputGroupPath.getName().startsWith("【组】") == false) {
            return;
        }
        if (inputGroupPath.isDirectory() == false) {
            return;
        }
        File[] sonPaths = inputGroupPath.listFiles(onlyDirectory);
        for (File sonPath : sonPaths) {
            if (Bo4MusicType.vertifyByName(sonPath.getName())) {
                System.out.println("        Type:" + sonPath.getName());
                listType.add(new Bo4MusicType(sonPath));
            }
        }
        calcuQuality();
    }

    public void calcuQuality() {

    }

    @Override
    public void calculate() {

    }

    @Override
    public void cycleCalculate() {

    }

    @Override
    public Bo3MusicGroup cycleCreate() {
        return null;
    }
}

