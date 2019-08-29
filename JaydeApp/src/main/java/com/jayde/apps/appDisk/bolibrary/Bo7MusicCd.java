package com.jayde.apps.appDisk.bolibrary;

import com.jayde.util.diskutils.OnlyDirectory;
import com.jayde.util.diskutils.OnlyFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-16 14:57
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-16 14:57
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Bo7MusicCd  extends BoFile{
    String id;
    String name;
    int filesCount;
    long filesSize;
    ArrayList filesList;
    InfoQuality infoQuality;
    static OnlyDirectory onlyDirectory = new OnlyDirectory();
    static OnlyFile onlyFile = new OnlyFile();
    List<Bo8MusicFile> listMusicFile = new ArrayList<>();
    List<Bo8MusicInfoFile> listMusicInfoFile = new ArrayList<>();
    List<Bo8OtherFile> listMusicOtherFile = new ArrayList<>();
    Bo6MusicAlbum parentAlbum = null;
    int mediaType = 0;

    public static boolean vertifyByName(String pathname) {
        if (pathname.startsWith("【CD"))
            return true;
        else
            return false;
    }

    public Bo7MusicCd(File inputCdPath) {
        if (inputCdPath.exists() == false) {
            return;
        }
        if (inputCdPath.getName().startsWith("【专辑】") == false) {
            return;
        }
        if (inputCdPath.isDirectory() == false) {
            return;
        }
        File[] sonFiles = inputCdPath.listFiles(onlyFile);
        for (File sonFile : sonFiles) {
            System.out.println("                  File:" + sonFile.getName());
            if (UtilMusicFile.isMusicFile(sonFile)) {
                listMusicFile.add(new Bo8MusicFile(sonFile,this));
            }
            listMusicOtherFile.add(new Bo8OtherFile(sonFile,this));
        }
        File[] sonPaths = inputCdPath.listFiles(onlyDirectory);
        for (File sonPath : sonPaths) {
            listMusicOtherFile.add(new Bo8OtherFile(sonPath,this));
        }

        calcuQuality();
    }

    public void calcuQuality() {
        int allCount = listMusicFile.size() + listMusicInfoFile.size() + listMusicOtherFile.size();
        float scoreTotal = 0f;
        for(Bo8MusicFile musicFile:listMusicFile){
//            scoreTotal = scoreTotal+InfoQualityMusic.vertifyMusicFile(file);
        }
    }

    @Override
    public void calculate() {

    }

    @Override
    public void cycleCalculate() {

    }

    @Override
    public Bo7MusicCd cycleCreate() {
        return null;
    }
}

