package com.jayde.apps.appDisk.bodisk;

import com.jayde.apps.appDisk.util.OnlyFileFilter;
import com.jayde.apps.appDisk.util.OnlyPathFilter;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.tag.Tag;

import java.io.File;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bodisk
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-09-12 16:30
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-09-12 16:30
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class UtilBoFile {
    final static OnlyFileFilter onlyFileFilter = new OnlyFileFilter();
    final static OnlyPathFilter onlyPathFilter = new OnlyPathFilter();

    public static BoFolder createBoFolder(File currentPath) {
        BoFolder currentFolder = new BoFolder(currentPath);
        File[] listFiles = currentPath.listFiles(onlyFileFilter);
        long selfFilesCount = 0l;
        long selfFolderCount = 0l;
        long selfFilesSize = 0l;
        long allFilesCount = 0l;
        long allFolderCount = 0l;
        long allFilesSize = 0l;

        for (File sonFile : listFiles) {
            BoFile sonBoFile = new BoFile(sonFile);
            if (sonBoFile.getFileType() == BoFile.FILE_TYPE_11_MUSIC_SONG) {
                sonBoFile = new BoMusicSongFile(sonFile);
            }
            System.out.println(sonBoFile + ":   " + sonBoFile.getFileType());
            sonBoFile.setParentFolder(currentFolder);
            currentFolder.getListSonFile().add(sonBoFile);
            selfFilesCount++;
            selfFilesSize += sonBoFile.getSize();
        }
        allFilesCount = selfFilesCount;
        allFilesSize = selfFilesSize;
        allFolderCount = selfFolderCount;

        File[] listPaths = currentPath.listFiles(onlyPathFilter);
        for (File sonPath : listPaths) {
            BoFolder sonBoFolder = createBoFolder(sonPath);
            sonBoFolder.setParentFolder(currentFolder);
            currentFolder.getListSonFolder().add(sonBoFolder);
            selfFolderCount++;
            allFilesCount += sonBoFolder.getAllFilesCount();
            allFilesSize += sonBoFolder.getAllFilesSize();
            allFolderCount += sonBoFolder.getAllFolderCount();
        }

        currentFolder.setSelfFilesCount(selfFilesCount);
        currentFolder.setSelfFilesSize(selfFilesSize);
        currentFolder.setSelfFolderCount(selfFolderCount);
        currentFolder.setAllFilesCount(allFilesCount);
        currentFolder.setAllFilesSize(allFilesSize);
        currentFolder.setAllFolderCount(allFolderCount);

        return currentFolder;
    }


    //只要有繁体，就判定为繁体。因为有些字，简体和繁体是一样的。比如“定影”。如果是这样的字，优先判定为简体。
    public static boolean isSimple(String str) {
        try {
            if (str.equals(new String(str.getBytes("GB2312"), "GB2312"))) {
                return true;
            } else {
                System.out.println("*** " + str);
                System.out.println("$$$ " + new String(str.getBytes("GB2312"), "GB2312"));
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUpperSuffix(String inputFileName) {
        if (inputFileName.contains(".") == false) {
            return "";
        }
        String[] subs = inputFileName.split("\\.");
        String suffix = subs[subs.length - 1].toUpperCase();
        return suffix.toUpperCase();
    }

    public static void main(String[] args) {
//        String rootPath = "/Users/mac/Desktop/标准文件夹[TEST]";
//        BoFolder rootFolder = UtilBoFile.createBoFolder(new File(rootPath));
//        System.out.println(rootFolder);
                String rootPath = "/Users/mac/Desktop/5.1dts/";
                File testFile = new File(rootPath+"(01) [Various Artists] Anonymus - Romance");
                try {
                    AudioFile f = AudioFileIO.read(testFile);
                    Tag tag = f.getTag();
                    AudioHeader audioHeader = f.getAudioHeader();
                }catch (Exception e){
                    System.out.println(e.getLocalizedMessage());
                }

    }

}
