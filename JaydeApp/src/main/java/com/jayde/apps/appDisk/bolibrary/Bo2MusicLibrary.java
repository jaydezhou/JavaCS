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
public class Bo2MusicLibrary extends BoFile {
    /*
    【音频】
     */

    List<Bo3MusicGroup> listGroup = new ArrayList<>();
    List<Bo8OtherFile> listOtherFiles = new ArrayList<>();
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

    public List<Bo3MusicGroup> getListGroup() {
        return listGroup;
    }

    public void setListGroup(List<Bo3MusicGroup> listGroup) {
        this.listGroup = listGroup;
    }

    public List<Bo8OtherFile> getListOtherFiles() {
        return listOtherFiles;
    }

    public void setListOtherFiles(List<Bo8OtherFile> listOtherFiles) {
        this.listOtherFiles = listOtherFiles;
    }

    public Bo1LibrarySet getParentLibrarySet() {
        return parentLibrarySet;
    }

    public void setParentLibrarySet(Bo1LibrarySet parentLibrarySet) {
        this.parentLibrarySet = parentLibrarySet;
    }

    public void calcuQuality() {

    }

    @Override
    public void calculate() {

    }

    @Override
    public void cycleCalculate() {

    }

    public static Bo2MusicLibrary cycleCreate(Bo1LibrarySet inputParentLibrarySet, File inputPath) {
        Bo2MusicLibrary library = new Bo2MusicLibrary(inputPath);
        library.setParentLibrarySet(inputParentLibrarySet);
        File[] paths = inputPath.listFiles(onlyDirectory);
        for (File path : paths) {
            Bo3MusicGroup bo3MusicGroup = Bo3MusicGroup.cycleCreate(library, path);
            library.getListGroup().add(bo3MusicGroup);
        }
        File[] files = inputPath.listFiles(onlyFile);
        for (File file : files) {
            Bo8OtherFile bo8OtherFile = new Bo8OtherFile(file);
            library.getListOtherFiles().add(bo8OtherFile);
        }
        return library;
    }
}
