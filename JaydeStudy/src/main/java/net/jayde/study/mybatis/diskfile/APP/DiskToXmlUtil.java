package net.jayde.study.mybatis.diskfile.APP;

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
import java.util.UUID;

public class DiskToXmlUtil {

    OnlyDirectory onlyDirectory;
    OnlyFile onlyFile;

    public static void main(String[] args) {
        DiskToXmlUtil diskToXmlUtil = new DiskToXmlUtil();
        try {
            diskToXmlUtil.fromDiskToXml("icloud", "/Users/mac/Library/Mobile Documents/com~apple~CloudDocs", "/Users/mac/Documents/icloud.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fromDiskToXml(String libraryName, String rootPathName, String saveFileName) throws IOException {
        File saveFile = new File(saveFileName);
        saveFile.createNewFile();

        // 创建Document对象
        Document document = DocumentHelper.createDocument();
        // 创建根节点
        Element eleRoot = document.addElement("ROOT");

        insertElementLibrary(eleRoot, libraryName, rootPathName);

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

    private void insertElementLibrary(Element eleRoot, String libraryName, String rootPathName) {
        Element eleLibrary = eleRoot.addElement("Library");
        eleLibrary.addAttribute("name", libraryName);
        eleLibrary.addAttribute("rootpath", rootPathName);
        System.out.println("preScan......");
        insertElementPath(eleLibrary, new File(rootPathName));
        return;
    }

    private Element insertElementPath(Element elePre, File currentPath) {

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
                    Element eleSonPath = insertElementPath(elePath, sonPath);
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

    private Element insertElementFile(Element elePath, File currentFile) {
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

//    public static PathAndFileCount preScanPathCount(String path) {
//        PathAndFileCount pathAndFileCount = new PathAndFileCount();
//
//        File dir = new File(path);
//        pathCount = 0;
//        fileCount = 0;
//        preCyclePath(dir);
//        pathAndFileCount.setPathCount(pathCount);
//        pathAndFileCount.setFileCount(fileCount);
//        return pathAndFileCount;
//    }
//
//    private static void preScanCyclePath(File path) {
//        pathCount++;
//        File[] files = path.listFiles();
//        if (files != null) {
//            for (File file : files) {
//                if (file.isDirectory()) {
//                    preCyclePath(file);
//                }
//                if (file.isFile()) {
//                    fileCount++;
//                }
//            }
//        }
//    }
}
