package net.jayde.study.mybatis;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

public class testF {
    File savedFile;
    FileWriter fw;
    BufferedWriter bw;
    BasicFileAttributeView basicView;
String splitStr = "::";
    public testF(String filename) {
        savedFile = new File(filename);
    }

    private void cycleFolder(File preFolder) {
        System.out.println("Folder:" + preFolder.getAbsolutePath());
        try {

            bw.write("Folder:" + preFolder.getAbsolutePath() + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File[] files = preFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try {

                        Path rootFile = Paths.get(file.getAbsolutePath());
                        basicView = Files.getFileAttributeView(rootFile, BasicFileAttributeView.class);
                        BasicFileAttributes basicFileAttributes = basicView.readAttributes();
                        bw.write("File:" + file.getName() + splitStr+ basicFileAttributes.creationTime() + splitStr + basicFileAttributes.lastAccessTime() + splitStr + basicFileAttributes.lastModifiedTime() + splitStr+ basicFileAttributes.size() + "\r\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        File[] folders = preFolder.listFiles();
        if (folders != null) {
            for (File folder : folders) {
                if (folder.isDirectory()) {
                    cycleFolder(folder);
                }
            }
        }

    }

    public void closeFile() {
        try {
            bw.flush();
            bw.close();
//            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeToFile(String path) {


        try {
            if (savedFile.exists()) {
                savedFile.delete();
                savedFile.createNewFile();
                savedFile.setWritable(true);
            }
            fw = new FileWriter(savedFile.getAbsolutePath(), true);
            bw = new BufferedWriter(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }


        long begin = new Date().getTime();
        File rootFile = new File(path);

        cycleFolder(rootFile);
        closeFile();
        long end = new Date().getTime();
        System.out.println("耗时：" + (end - begin));
    }

    public void readFromFile() {
//        try {
//            InputStreamReader reader = new InputStreamReader(new FileInputStream(savedFile));
//            BufferedReader br = new BufferedReader(reader);
//            String line = "";
//            line = br.readLine();
//            while (line != null) {
//                if (line.startsWith("Folder:")) {
//                    System.out.println(line);
//                } else {
////                    System.out.println("    " + line);
//                    analyzeFile(line);
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
    }

    void analyzeFile(String line) {
        String lineinfo = line.substring(5);
        String[] lines = lineinfo.split(",");
        for (String str : lines) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        testF t = new testF("/Users/mac/Documents/专业知识.txt");
        t.writeToFile("/Users/mac/Library/Mobile Documents/com~apple~CloudDocs/文档集/1.工作文档集/1.4）专业知识");
//        t.readFromFile();
    }
}
