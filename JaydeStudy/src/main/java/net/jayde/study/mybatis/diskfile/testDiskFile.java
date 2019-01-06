package net.jayde.study.mybatis.diskfile;

import net.jayde.study.mybatis.diskfile.BO.DiskFile;
import net.jayde.study.mybatis.diskfile.BO.DiskLibrary;
import net.jayde.study.mybatis.diskfile.BO.DiskMapper;
import net.jayde.study.mybatis.diskfile.BO.DiskPath;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import java.util.UUID;

public class testDiskFile {
    public static void main(String[] args) {

        String resource = "net/jayde/study/mybatis/diskfile/mybatis-diskfile.xml";
        String savedfile = "/Users/mac/Documents/st4t音频文件夹.txt";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DiskMapper mapper =
                    sqlSession.getMapper(DiskMapper.class);

//            DiskLibrary library1 = mapper.getLibraryById("111");
//            System.out.println(library1);
//
//            DiskLibrary library2 = mapper.getLibraryByName("库名");
//            System.out.println(library2);

            DiskLibrary library = new DiskLibrary();
            library.setLibraryId(UUID.randomUUID().toString());
            library.setLibraryName("st4t音频文件夹");
            library.setLibraryPathCount(0);
            if (mapper.getLibraryByName(library.getLibraryName()) == null) {
                mapper.insertDiskLibrary(library);
            }

            try {
                reader = new InputStreamReader(new FileInputStream(new File(savedfile)));
                BufferedReader br = new BufferedReader(reader);
                String line = "";
                line = br.readLine();
//                DiskPath currentPath = null;
//                Stack<DiskPath> stackPath = new Stack<>();
                while (line != null) {
                    if (line.startsWith("Folder:")) {
                        DiskPath diskPath = testDiskFile.createDiskPath(line);
                        diskPath.setPreLibraryId(library.getLibraryId());
//                        if (currentPath == null) {
//                            diskPath.setPrePathId("-1");
//                            stackPath.push(diskPath);
//                        } else {
//
//                        }
                        mapper.insertDiskFolder(diskPath);
//                        System.out.println(line);
                    } else {
//                    System.out.println("    " + line);
                        DiskFile diskFile = testDiskFile.createDiskFile(line);
                        diskFile.setPreLibraryId(library.getLibraryId());
                        mapper.insertDiskFile(diskFile);
                    }
                    line = br.readLine();
                }
                br.close();
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
//
//            DiskPath path1 = new DiskPath();
//            path1.setPathId(UUID.randomUUID().toString());
//            path1.setPathName("/Volumes/Seagate Backup Plus Drive/音频");
//            path1.setSelfPathCount(22);
//            path1.setSonSize(999999999);
//            if(mapper.getPathByName(path1.getPathName())==null) {
//                mapper.insertDiskFolder(path1);
//            }
//
//            DiskFile file1 = new DiskFile();
//            file1.setFileId(UUID.randomUUID().toString());
//            if(mapper.getFileById(file1.getFileId())==null){
//                mapper.insertDiskFile(file1);
//            }

            sqlSession.commit();

        } finally {
            sqlSession.close();

        }

    }

    public static DiskPath createDiskPath(String str) {
        DiskPath diskPath = new DiskPath();
        diskPath.setPathId(UUID.randomUUID().toString());
        diskPath.setPathFullText(str.substring(7));
        return diskPath;
    }

    public static DiskFile createDiskFile(String str) {
        DiskFile diskFile = new DiskFile();

        String[] strs = str.substring(5).split("::");

        if(strs.length==5){
            diskFile.setFileId(UUID.randomUUID().toString());
            diskFile.setFileName(strs[0]);
            diskFile.setCreateDatetime(strs[1]);
            diskFile.setModifyDatetime(strs[3]);
            diskFile.setFileSize(Long.parseLong(strs[4]));
//            System.out.println(str);
//            System.out.println(diskFile);
        }
        if (strs.length != 5) {
            System.out.println("--------------");
            System.out.println(strs.length);
            System.out.println(str);
            System.out.println(diskFile);
        }

        return diskFile;
    }
}
