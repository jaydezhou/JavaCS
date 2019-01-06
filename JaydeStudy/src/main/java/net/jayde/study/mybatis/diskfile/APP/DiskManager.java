package net.jayde.study.mybatis.diskfile.APP;

import net.jayde.study.mybatis.diskfile.BO.DiskFile;
import net.jayde.study.mybatis.diskfile.BO.DiskLibrary;
import net.jayde.study.mybatis.diskfile.BO.DiskMapper;
import net.jayde.study.mybatis.diskfile.BO.DiskPath;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import java.util.List;
import java.util.UUID;

public class DiskManager {
    static String resource = "net/jayde/study/mybatis/diskfile/mybatis-diskfile.xml";
    DiskMapper diskMapper = null;

    public static void main(String[] args) {
        DiskManager manager = new DiskManager();
        manager.importFromSavedFile("icloud", "/Users/mac/Documents/icloud.txt");

    }

    public void createSavedFile(String rootPath, String libraryName, String savedFileName) {

    }

    public void importFromSavedFile(String libraryName, String savedFileName) {

        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println(diskMapper);
        if (diskMapper == null) {
            diskMapper = sqlSession.getMapper(DiskMapper.class);
            System.out.println(diskMapper);
        }

//        DiskPath diskPath = diskMapper.getPathByName(diskl);
//        System.out.println(diskPath);
//
//        diskPath.setSelfFileCount(999);
//        diskMapper.updateDiskPathInfo(diskPath);

        DiskLibrary library = new DiskLibrary();
        library.setLibraryId(UUID.randomUUID().toString());
        library.setLibraryName(libraryName);
        library.setLibraryPathCount(0);

        if (diskMapper.getLibraryByName(libraryName) != null) {
            System.out.println("Library exist,Stop");
            library = diskMapper.getLibraryByName(libraryName);
            System.out.println(library);
        } else {
            diskMapper.insertDiskLibrary(library);
            sqlSession.commit();
        }

        int maxLevel = diskMapper.findMaxLevelByLibraryId(library.getLibraryId());
        int minLevel = diskMapper.findMinLevelByLibraryId(library.getLibraryId());


        DiskPath rootPath = diskMapper.getPathByFullText(library.getLibraryRootPath());
        System.out.println(rootPath);

        List<DiskPath> listPath = diskMapper.getAllPathsByLibraryId(library.getLibraryId());
        System.out.println(listPath.size());

        for (DiskPath diskpath : listPath) {
//            System.out.println(diskpath);


            String pathFullText = diskpath.getPathFullText();
            int i = pathFullText.lastIndexOf("/");
//            System.out.println(pathFullText);
            String prePathFullText = pathFullText.substring(0, i);
//            System.out.println(prePathFullText);
//            DiskPath preDiskPath = diskMapper.getPathByFullText(prePathFullText);
//            if (preDiskPath == null) {
//                diskpath.setPrePathId(null);
//            } else {
//                diskpath.setPrePathId(preDiskPath.getPathId());
//            }
//            diskMapper.updateDiskPathInfo(diskpath);
        }

//        try {
//            reader = new InputStreamReader(new FileInputStream(new File(savedFileName)));
//            BufferedReader br = new BufferedReader(reader);
//            String line = "";
//            DiskPath preDiskPath = null;
//            line = br.readLine();
//            while (line != null) {
//                if (line.startsWith("Folder:")) {
//                    DiskPath diskPath = createDiskPathByStr(library, line);
//                    diskPath.setPreLibraryId(library.getLibraryId());
//                    diskMapper.insertDiskFolder(diskPath);
//                    preDiskPath = diskPath;
//                } else {
//                    DiskFile diskFile = genaDiskFileByStr(preDiskPath, line);
//                    diskMapper.insertDiskFile(diskFile);
//                }
//                line = br.readLine();
//            }
//            br.close();
//            reader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        /*由低到高，统计自身*/
        for (int iLevel = maxLevel; iLevel >= minLevel; iLevel--) {
            List<DiskPath> listpath = diskMapper.getAllPathsByLevel(library.getLibraryId(), iLevel);
            for (DiskPath diskPath : listpath) {
                System.out.println(diskPath.getLevel() + ":" + diskPath);
                List<DiskFile> listFile = diskMapper.getAllFilesByPrepathId(diskPath.getPathId());
                long selfFileCount = 0;
                long selfSize = 0;
                for (DiskFile diskfile : listFile) {
                    selfFileCount++;
                    selfSize += diskfile.getFileSize();
                }
                diskPath.setSelfFileCount(selfFileCount);
                diskPath.setSelfSize(selfSize);
                List<DiskPath> listSonPath = diskMapper.getAllPathsByPrepathId(diskPath.getPathId());
                if (listSonPath == null) {
                    diskPath.setSelfPathCount(0);
                } else {
                    diskPath.setSelfPathCount(listSonPath.size());
                }
//                DiskPath preDiskPath = diskMapper.getPathById(diskPath.getPrePathId());
//                if(preDiskPath!=null){
//                    preDiskPath.setSelfPathCount(preDiskPath.getSelfPathCount()+1);
//                    preDiskPath.setSonPathCount(preDiskPath.getSonPathCount()+diskPath.getSonPathCount());
//                    preDiskPath.setSonFileCount(preDiskPath.getSonFileCount()+diskPath.getSonFileCount());
//                    preDiskPath.setSonSize(preDiskPath.getSonSize()+diskPath.getSonSize());
//                    diskMapper.updateDiskPathInfo(preDiskPath);
//                }
                diskMapper.updateDiskPathInfo(diskPath);
            }
        }
        sqlSession.commit();

        /*由高到低，统计子集*/
        for (int iLevel = maxLevel ; iLevel >= minLevel; iLevel--) {
            List<DiskPath> listpath = diskMapper.getAllPathsByLevel(library.getLibraryId(), iLevel);
            for (DiskPath diskPath : listpath) {
                System.out.println(diskPath.getLevel() + ":" + diskPath);
                List<DiskPath> listSonPath = diskMapper.getAllPathsByPrepathId(diskPath.getPathId());
                if(listSonPath==null || listSonPath.size()==0){
                    diskPath.setSonPathCount(0);
                    diskPath.setSonFileCount(diskPath.getSelfFileCount());
                    diskPath.setSonSize(diskPath.getSelfSize());
                }else {
                    long pathCount = diskPath.getSelfPathCount();
                    long fileCount = diskPath.getSelfFileCount();
                    long size = diskPath.getSelfSize();
                    for (DiskPath sonDiskPath : listSonPath) {
                        pathCount += sonDiskPath.getSonPathCount();
                        fileCount += sonDiskPath.getSonFileCount();
                        size += sonDiskPath.getSonSize();
                    }
                    diskPath.setSonPathCount(pathCount);
                    diskPath.setSonFileCount(fileCount);
                    diskPath.setSonSize(size);
                }
                diskMapper.updateDiskPathInfo(diskPath);
            }
            sqlSession.commit();
        }

        sqlSession.commit();
        sqlSession.close();

    }


    private static DiskPath createDiskPathByStr(DiskLibrary library, String str) {
        DiskPath currentDiskPath = new DiskPath();
        currentDiskPath.setPathId(UUID.randomUUID().toString());
        currentDiskPath.setPathFullText(str.substring(7));
        currentDiskPath.setPreLibraryId(library.getLibraryId());
        currentDiskPath.setLevel(str.split("/").length);
        return currentDiskPath;
    }

    private static DiskFile genaDiskFileByStr(DiskPath preDiskPath, String str) {
        DiskFile currentDiskFile = new DiskFile();

        String[] strs = str.substring(5).split("::");

        if (strs.length == 5) {
            currentDiskFile.setFileId(UUID.randomUUID().toString());
            currentDiskFile.setFileName(strs[0]);
            currentDiskFile.setCreateDatetime(strs[1]);
            currentDiskFile.setModifyDatetime(strs[3]);
            currentDiskFile.setFileSize(Long.parseLong(strs[4]));
            currentDiskFile.setPreLibraryId(preDiskPath.getPreLibraryId());
            currentDiskFile.setPrePathId(preDiskPath.getPathId());
        }
        if (strs.length != 5) {
            System.out.println("--------------");
            System.out.println(strs.length);
            System.out.println(str);
            System.out.println(currentDiskFile);
        }

        return currentDiskFile;
    }


}
