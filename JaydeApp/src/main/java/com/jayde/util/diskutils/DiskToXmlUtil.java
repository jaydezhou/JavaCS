package com.jayde.util.diskutils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class DiskToXmlUtil {

    static OnlyDirectory onlyDirectory;
    static OnlyFile onlyFile;

    public static void main(String[] args) {
        DiskToXmlUtil diskToXmlUtil = new DiskToXmlUtil();
//        try {
//            diskToXmlUtil.fromDiskToXml("icloud", "/Users/mac/Library/Mobile Documents/com~apple~CloudDocs", "/Users/mac/Documents/icloud.xml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void fromDiskToXml(String libraryName, String rootPathName, String saveFileName,PathAndFileCount pathAndFileCount) throws IOException {
        File saveFile = new File(saveFileName);
        saveFile.createNewFile();
        System.out.println(saveFile.exists());
        System.out.println(saveFile);


//        通过DocumentHelper类的createDocument()创建Document对象
//        通过Document的addElement()方法创建节点
//        通过Element的addAttribute()方法为节点添加属性
//        通过Element的setText()方法为节点设置内容
//        通过OutputFormat的createPrettyPrint()方法创建OutputFormat对象（会自动缩进、换行）
//        创建XMLWriter对象，将目的文件包装成OutputStream传入构造方法中，并将OutputFormat对象一并传入其中
//        通过XMLWriter的write()方法生成XML文件，并将Document对象作为参数传入
//        关闭XMLWriter对象

        // 创建Document对象
        Document document = DocumentHelper.createDocument();
        // 创建根节点
        Element eleRoot = document.addElement("ROOT");

        insertElementLibrary(eleRoot, libraryName, rootPathName, pathAndFileCount);

        // 创建输出格式(OutputFormat对象)
        OutputFormat format = OutputFormat.createPrettyPrint();

        ///设置输出文件的编码
//      format.setEncoding("GBK");

        // 创建XMLWriter对象
        XMLWriter writer = new XMLWriter(new FileOutputStream(saveFile), format);
        //设置不自动进行转义
        writer.setEscapeText(false);
        // 生成XML文件
        writer.write(document);
        //关闭XMLWriter对象
        writer.close();
    }

    private static void insertElementLibrary(Element eleRoot, String libraryName, String rootPathName,PathAndFileCount pathAndFileCount) {
        Element elementLibrary = eleRoot.addElement("Library");
        elementLibrary.addAttribute("name", libraryName);
        elementLibrary.addAttribute("rootpath", rootPathName);
        System.out.println("preScan......");
//        PathAndFileCount pathAndFileCount = prePathCount(rootPathName);
        Date startTime = new Date();
        insertElementPath(elementLibrary, new File(rootPathName), pathAndFileCount);
        Date endTime = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:S");
        elementLibrary.addAttribute("startTime",dateFormat.format(startTime));
        elementLibrary.addAttribute("endTime",dateFormat.format(endTime));
        return;
    }

    private static Element insertElementPath(Element elePre, File currentPath, PathAndFileCount pathAndFileCount) {
        pathAndFileCount.doPathAdd();
//        System.out.println("done/all:" + pathAndFileCount.getDoPathCount()+ "/" + pathAndFileCount.getAllPathCount()+"    "+pathAndFileCount.getDoPathCount()*100d/pathAndFileCount.getAllPathCount()+"%");
        pathAndFileCount.showPathWork();
        Element elePath = elePre.addElement("Path");
        elePath.addAttribute("id", UUID.randomUUID().toString());
        elePath.addAttribute("name", currentPath.getName());
        if (elePre.getName().equals("Path")) {
            elePath.addAttribute("pid", elePre.attributeValue("id"));
        }

        long selfPathCount = 0;
        long selfFileCount = 0;
        long selfSize = 0;

        File[] files = currentPath.listFiles(onlyFile);
        if (files != null && files.length > 0) {
            for (File sonFile : files) {
                if (sonFile.isFile()) {
                    Element eleSonFile = insertElementFile(elePath, sonFile);
                    selfFileCount++;
                    selfSize += Long.parseLong(eleSonFile.attributeValue("size"));
                }
            }
        }

        long sonPathCount = 0;
        long sonFileCount = 0;
        long sonSize = 0;

        sonFileCount = selfFileCount;
        sonPathCount = 0;
        sonSize = selfSize;

        File[] paths = currentPath.listFiles(onlyDirectory);
        if (paths != null && paths.length > 0) {
            for (File sonPath : paths) {
                if (sonPath.isDirectory()) {
                    Element eleSonPath = insertElementPath(elePath, sonPath, pathAndFileCount);
                    selfPathCount++;
                    sonPathCount++;
                    sonFileCount += Long.parseLong(eleSonPath.attributeValue("sonFileCount"));
                    sonPathCount += Long.parseLong(eleSonPath.attributeValue("sonPathCount"));
                    sonSize += Long.parseLong(eleSonPath.attributeValue("sonSize"));
                }
            }
        }

        elePath.addAttribute("sonPathCount", String.valueOf(sonPathCount));
        elePath.addAttribute("sonFileCount", String.valueOf(sonFileCount));
        elePath.addAttribute("sonSize", String.valueOf(sonSize));

//        elePath.addAttribute("son", sonPathCount + "-" + sonFileCount + "-" + sonSize);
        elePath.addAttribute("selfPathCount", String.valueOf(selfPathCount));
        elePath.addAttribute("selfFileCount", String.valueOf(selfFileCount));
        elePath.addAttribute("selfSize", String.valueOf(selfSize));
//        elePath.addAttribute("self", selfPathCount + "-" + selfFileCount + "-" + selfSize);

        return elePath;
    }

    private static Element insertElementFile(Element elePath, File currentFile) {
        BasicFileAttributeView basicView;
        Element eleFile = null;
        try {
            Path rootFile = Paths.get(currentFile.getAbsolutePath());
            basicView = Files.getFileAttributeView(rootFile, BasicFileAttributeView.class);
            BasicFileAttributes basicFileAttributes = basicView.readAttributes();

            eleFile = elePath.addElement("File");
            eleFile.addAttribute("id", UUID.randomUUID().toString());
            eleFile.addAttribute("name", currentFile.getName());
            eleFile.addAttribute("pid", elePath.attributeValue("id"));
            eleFile.addAttribute("size", basicFileAttributes.size() + "");
            eleFile.addAttribute("created", basicFileAttributes.creationTime().toString());
            eleFile.addAttribute("modified", basicFileAttributes.lastModifiedTime().toString());
            eleFile.addAttribute("accesss", basicFileAttributes.lastAccessTime().toString());
        } catch (IOException e) {
        }

        return eleFile;
    }

    public static PathAndFileCount prePathCount(String path) {
        PathAndFileCount pathAndFileCount = new PathAndFileCount();

        File dir = new File(path);

        Date startTime = new Date();
        preCyclePath(dir, pathAndFileCount);
        Date endTime = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:S");
        System.out.println("preScan:   begin:" +dateFormat.format(startTime)+"   end:"+dateFormat.format(endTime));
        return pathAndFileCount;
    }

    private static void preCyclePath(File path, PathAndFileCount pathAndFileCount) {
        pathAndFileCount.allPathAdd();
        File[] files = path.listFiles(onlyFile);

        if (files != null) {
            for (File file : files) {
                if (file != null && file.isFile()) {
                    pathAndFileCount.allFileAdd();
                }
            }
        }

        File[] paths = path.listFiles(onlyDirectory);
        if (paths != null) {
            for (File path1 : paths) {
                if (path1 != null && path1.isDirectory()) {
                    preCyclePath(path1, pathAndFileCount);
                }
            }
        }

    }


}
