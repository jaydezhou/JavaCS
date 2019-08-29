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
public class Bo2MusicLibrary  extends BoFile{
    static OnlyDirectory onlyDirectory = new OnlyDirectory();
    /*
    【音频】
     */

    File libraryFile;
    long qualityValue = 0l;
    List<Bo3MusicGroup> listGroup = new ArrayList<>();
    Bo1LibrarySet parentLibrarySet = null;

    public static boolean vertifyByName(String pathname) {
        if (pathname.equals("【音频】"))
            return true;
        else
            return false;
    }

    public Bo2MusicLibrary(File inputLibraryPath) {
        if (inputLibraryPath.exists() == false) {
            return;
        }
        if (inputLibraryPath.getName().equals("【音频】") == false) {
            return;
        }
        if (inputLibraryPath.isDirectory() == false) {
            return;
        }
        File[] sonPaths = inputLibraryPath.listFiles(onlyDirectory);
        for (File sonPath : sonPaths) {
            if (Bo3MusicGroup.vertifyByName(sonPath.getName())) {
                System.out.println("      Group:" + sonPath.getName());
                listGroup.add(new Bo3MusicGroup(sonPath));
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
    public Bo2MusicLibrary cycleCreate() {
        return null;
    }
}
