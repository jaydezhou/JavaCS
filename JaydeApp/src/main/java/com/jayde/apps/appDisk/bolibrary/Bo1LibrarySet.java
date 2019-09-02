package com.jayde.apps.appDisk.bolibrary;


import com.jayde.util.diskutils.OnlyDirectory;

import javax.naming.InitialContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-13 11:00
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-13 11:00
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Bo1LibrarySet extends BoFile {
    /*
    标准文件夹
      【音频】
        【组】流行音乐(HIFI)
          【类】华语男
            【歌手】张学友
              【专辑:张学友】雪狼湖[2CD]
                【CD:张学友】雪狼湖[CD1]
     */

    File librarySetFile;
    long qualityValue = 0l;
    List<Bo2MusicLibrary> listLibrary = new ArrayList<>();
    List<Bo8OtherFile> listOtherFiles = new ArrayList<>();

    public void calcQuality() {

    }

    public Bo1LibrarySet(String inputLibrarySetPath) {
        this(new File(inputLibrarySetPath));
    }

    public Bo1LibrarySet(File path) {

        setSelfFile(path);
        if (path.exists() == false) {
            return;
        }
        if (path.getName().equals("标准文件夹") == false) {
            return;
        }
        if (path.isDirectory() == false) {
            return;
        }
        calcuQuality();
    }

    public File getLibrarySetFile() {
        return librarySetFile;
    }

    public void setLibrarySetFile(File librarySetFile) {
        this.librarySetFile = librarySetFile;
    }

    public long getQualityValue() {
        return qualityValue;
    }

    public void setQualityValue(long qualityValue) {
        this.qualityValue = qualityValue;
    }

    public List<Bo2MusicLibrary> getListLibrary() {
        return listLibrary;
    }

    public void setListLibrary(List<Bo2MusicLibrary> listLibrary) {
        this.listLibrary = listLibrary;
    }

    public List<Bo8OtherFile> getListOtherFiles() {
        return listOtherFiles;
    }

    public void setListOtherFiles(List<Bo8OtherFile> listOtherFiles) {
        this.listOtherFiles = listOtherFiles;
    }

    public void calcuQuality() {

    }

    public static void main(String[] args) {
        Bo1LibrarySet librarySet = Bo1LibrarySet.cycleCreate(new File("/Users/mac/Desktop/标准文件夹"));
        librarySet.cycleCalculate();
        librarySet.cycleShowTree();
//        librarySet.cycleCalculate();
    }

    @Override
    public float calculate() {
        return 0f;
    }

    @Override
    public float cycleCalculate() {
        return UtilMusic.cycleCalculate(this);
    }

    public static Bo1LibrarySet cycleCreate(File inputPath) {
        Bo1LibrarySet librarySet = new Bo1LibrarySet(inputPath);
        File[] paths = inputPath.listFiles(onlyDirectory);
        for (File path : paths) {
            Bo2MusicLibrary bo2MusicLibrary = Bo2MusicLibrary.cycleCreate(librarySet, path);
            librarySet.getListLibrary().add(bo2MusicLibrary);
        }
        File[] files = inputPath.listFiles(onlyFile);
        for (File file : files) {
            if (BoFile.getFileType(file) != BoFile.MUSIC_OSFILE) {
                Bo8OtherFile bo8OtherFile = new Bo8OtherFile(file);
                librarySet.getListOtherFiles().add(bo8OtherFile);
            }
        }

        return librarySet;
    }

    public void cycleShowTree() {
        System.out.print(BoFile.TREE_BLANK1);
        System.out.println(this);
        for (Bo2MusicLibrary library : listLibrary) {
            library.cycleShowTree();
        }
        for (Bo8OtherFile otherFile : listOtherFiles) {
            System.out.print(BoFile.TREE_BLANK2);
            System.out.println(otherFile);
        }
    }

}
