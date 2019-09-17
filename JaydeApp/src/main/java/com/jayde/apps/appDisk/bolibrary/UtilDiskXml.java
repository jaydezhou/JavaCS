package com.jayde.apps.appDisk.bolibrary;

import com.jayde.apps.appDisk.bodisk.CommonTag;
import com.jayde.util.diskutils.OnlyDirectory;
import com.jayde.util.diskutils.OnlyFile;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jaudiotagger.audio.AudioHeader;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-09-03 11:13
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-09-03 11:13
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class UtilDiskXml {


    static OnlyDirectory onlyDirectory = new OnlyDirectory();
    static OnlyFile onlyFile = new OnlyFile();
    static String SPLIT_MAC = "/";
    static String SPLIT_WIN = "";
    static int scanFileCount = 0;


    public static void createXml(String rootpathname, String xmlpathname, boolean onlyVisible) {
        File fromPath = new File(rootpathname);
        String pathName = fromPath.getName();
        String toPath = xmlpathname + "/" + pathName + ".xml";

        //创建一个xml文档
        Document doc = DocumentHelper.createDocument();
        //向xml文件中添加注释
        doc.addComment("这里是注释");
        //创建一个名为students的节点，因为是第一个创建，所以是根节点,再通过doc创建一个则会报错。
        Element root = doc.addElement("ROOT");
        //用于格式化xml内容和设置头部标签
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置xml文档的编码为utf-8
        format.setEncoding("utf-8");
        Writer out;
        try {
            //创建一个输出流对象
            out = new FileWriter(toPath);
            //创建一个dom4j创建xml的对象
            XMLWriter writer = new XMLWriter(out, format);
            //调用write方法将doc文档写到指定路径
            writer.write(doc);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public static void scanPaths(String rootpathname, String xmlpathname, boolean onlyVisible) {
        File fromPath = new File(rootpathname);
        String pathName = fromPath.getName();
        String toPath = xmlpathname + "/" + pathName + ".xml";
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("ROOT");
        root.addAttribute("name", pathName);
        root.addAttribute("path", fromPath.getAbsolutePath());
        cycleScanPath(fromPath, root, onlyVisible, 0);

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        Writer out;
        try {
            //创建一个输出流对象
            out = new FileWriter(toPath);
            //创建一个dom4j创建xml的对象
            XMLWriter writer = new XMLWriter(out, format);
            //调用write方法将doc文档写到指定路径
            writer.write(doc);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    private static void cycleScanPath(File currentPath, Element parentEle, boolean onlyVisible, int level) {
        if (onlyVisible == false || onlyVisible && currentPath.isHidden() == false) {
            System.out.println("cycleScanPath:" + currentPath.getName());
            Element currentEle = parentEle.addElement("Path");
            String filename = currentPath.getName();
            currentEle.addAttribute("name", filename);
            currentEle.addAttribute("scan", "0");
            currentEle.addAttribute("selfFilesCount", "0");
            currentEle.addAttribute("selfPathsCount", "0");
            currentEle.addAttribute("selfFilesSize", "0");
            currentEle.addAttribute("allFilesCount", "0");
            currentEle.addAttribute("allPathsCount", "0");
            currentEle.addAttribute("allFilesSize", "0");
            String attribute = "";
            if (currentPath.canRead()) {
                attribute = "R";
            }
            if (currentPath.canWrite()) {
                attribute = attribute.concat("W");
            }
            if (currentPath.canExecute()) {
                attribute = attribute.concat("E");
            }
            if (currentPath.isHidden() == false) {
                attribute = attribute.concat("V");
            }
            currentEle.addAttribute("attributes", attribute);

            if (Bo1LibrarySet.isLibrarySet(filename)) {
                level = 1;
            }

            if (level > 0) {
                currentEle.addAttribute("score", "0");
                switch (level) {
                    case 1: {
                        currentEle.addAttribute("type", "LibrarySet");
                        break;
                    }
                    case 2: {
                        currentEle.addAttribute("type", "Library");
                        break;
                    }
                    case 3: {
                        currentEle.addAttribute("type", "Group");
                        break;
                    }
                    case 4: {
                        currentEle.addAttribute("type", "Type");
                        break;
                    }
                    case 5: {
                        currentEle.addAttribute("type", "Singer");
                        break;
                    }
                    case 6: {
                        currentEle.addAttribute("type", "Album");
                        break;
                    }
                    case 7: {
                        currentEle.addAttribute("type", "Cd");
                        break;
                    }
                }
                File[] sonpaths = currentPath.listFiles(onlyDirectory);
                for (File sonpath : sonpaths) {
                    cycleScanPath(sonpath, currentEle, onlyVisible, level + 1);
                }
            } else {
                File[] sonpaths = currentPath.listFiles(onlyDirectory);
                for (File sonpath : sonpaths) {
                    cycleScanPath(sonpath, currentEle, onlyVisible, level);
                }
            }
        }

    }

    public static void scanFiles(String rootpathname, String xmlpathname, boolean onlyVisible) {
        File fromPath = new File(rootpathname);
        String pathName = fromPath.getName();
        String xmlfilename = xmlpathname + "/" + pathName + ".xml";
        Document doc = null;
        try {
            doc = new SAXReader().read(new File(xmlfilename));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootEle = doc.getRootElement();
        System.out.println(rootEle);
        String rootPath = rootEle.attributeValue("path");

        for (Object obj : rootEle.elements("Path")) {
            Element elePath = (Element) obj;
            cycleScanFile(rootPath, elePath, onlyVisible);
        }

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        Writer out;
        try {
            //创建一个输出流对象
            out = new FileWriter(xmlfilename);
            //创建一个dom4j创建xml的对象
            XMLWriter writer = new XMLWriter(out, format);
            //调用write方法将doc文档写到指定路径
            writer.write(doc);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

    private static void cycleScanFile(String currentPath, Element currentEle, boolean onlyVisible) {
        System.out.println("cycleScanFile:" + currentPath);
        String isScan = currentEle.attributeValue("scan");
        if (isScan.equals("0") || isScan.equals("")) {
            File path = new File(currentPath);
            File[] sonfiles = path.listFiles(onlyFile);
            long selfFilesSize = 0l;
            for (File sonfile : sonfiles) {
                if (onlyVisible == false || onlyVisible && sonfile.isHidden() == false) {
                    Element eleFile = currentEle.addElement("File");
                    eleFile.addAttribute("name", sonfile.getName());
                    eleFile.addAttribute("size", sonfile.length() + "");
                    eleFile.addAttribute("modifyDate", sonfile.lastModified() + "");
                    String attribute = "";
                    if (sonfile.canRead()) {
                        attribute = "R";
                    }
                    if (sonfile.canWrite()) {
                        attribute = attribute.concat("W");
                    }
                    if (sonfile.canExecute()) {
                        attribute = attribute.concat("E");
                    }
                    if (sonfile.isHidden() == false) {
                        attribute = attribute.concat("V");
                    }
                    eleFile.addAttribute("attributes", attribute);
                    selfFilesSize += sonfile.length();

                    //TODO:判断是否在集合中
                    if (currentEle.attribute("score") != null) {
                        //TODO:判断是否集合文件，这个有点难，会误判集合中的非集合文件
                        eleFile.addAttribute("type", "");
                        eleFile.addAttribute("score", "0");
                        currentEle.addAttribute("score", "1");
                    }
                }
            }
            currentEle.addAttribute("scan", "1");
            currentEle.addAttribute("selfFilesCount", currentEle.elements("File").size() + "");
            currentEle.addAttribute("selfPathsCount", currentEle.elements("Path").size() + "");
            currentEle.addAttribute("selfFilesSize", selfFilesSize + "");
            currentEle.addAttribute("allFilesCount", "0");
            currentEle.addAttribute("allPathsCount", "0");
            currentEle.addAttribute("allFilesSize", "0");
            //TODO:修改score

        }
        updateFileWhenScan();
        for (Object obj : currentEle.elements("Path")) {
            Element elePath = (Element) obj;
            cycleScanFile(currentPath + SPLIT_MAC + elePath.attributeValue("name"), elePath, onlyVisible);
        }
    }


    public static void countFile(String rootpathname, String xmlpathname) {
        File fromPath = new File(rootpathname);
        String pathName = fromPath.getName();
        String xmlfilename = xmlpathname + "/" + pathName + ".xml";
        Document doc = null;
        try {
            doc = new SAXReader().read(new File(xmlfilename));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootEle = doc.getRootElement();
        System.out.println(rootEle);
        String rootPath = rootEle.attributeValue("path");

        for (Object obj : rootEle.elements("Path")) {
            Element elePath = (Element) obj;
            cycleCountFile(rootPath, elePath);
        }

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        Writer out;
        try {
            //创建一个输出流对象
            out = new FileWriter(xmlfilename);
            //创建一个dom4j创建xml的对象
            XMLWriter writer = new XMLWriter(out, format);
            //调用write方法将doc文档写到指定路径
            writer.write(doc);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

    private static void cycleCountFile(String currentPath, Element currentEle) {
        System.out.println("cycleCountFile:" + currentPath);
        List<Element> list = currentEle.elements("Path");
        if (list.size() == 0) {
            currentEle.addAttribute("allFilesCount", currentEle.attributeValue("selfFilesCount"));
            currentEle.addAttribute("allPathsCount", "0");
            currentEle.addAttribute("allFilesSize", currentEle.attributeValue("selfFilesSize"));
        } else {
            long allFilesCount = 0l;
            long allPathsCount = 0l;
            long allFilesSize = 0l;
            long sonFilesCount = 0l;
            long sonPathsCount = 0l;
            long sonFilesSize = 0l;
            for (Element eleSonPath : list) {
                cycleCountFile(currentPath + SPLIT_MAC + eleSonPath.attributeValue("name"), eleSonPath);
                sonFilesCount = Long.parseLong(eleSonPath.attributeValue("allFilesCount"));
                sonPathsCount = Long.parseLong(eleSonPath.attributeValue("allPathsCount"));
                sonFilesSize = Long.parseLong(eleSonPath.attributeValue("allFilesSize"));
                allFilesCount += sonFilesCount;
                allPathsCount += sonPathsCount;
                allFilesSize += sonFilesSize;
            }
            allFilesCount += Long.parseLong(currentEle.attributeValue("selfFilesCount"));
            allPathsCount += Long.parseLong(currentEle.attributeValue("selfPathsCount"));
            allFilesSize += Long.parseLong(currentEle.attributeValue("selfFilesSize"));
            currentEle.addAttribute("allFilesCount", allFilesCount + "");
            currentEle.addAttribute("allPathsCount", allPathsCount + "");
            currentEle.addAttribute("allFilesSize", allFilesSize + "");
        }


    }

    public static void readFileInfo(String rootpathname, String xmlpathname, boolean onlyVisible) {
        File fromPath = new File(rootpathname);
        String pathName = fromPath.getName();
        String xmlfilename = xmlpathname + "/" + pathName + ".xml";
        Document doc = null;
        try {
            doc = new SAXReader().read(new File(xmlfilename));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootEle = doc.getRootElement();
        System.out.println(rootEle);
        String rootPath = rootEle.attributeValue("path");

        for (Object obj : rootEle.elements("Path")) {
            Element elePath = (Element) obj;
            cycleReadFileInfo(rootPath, elePath, onlyVisible);
        }

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        Writer out;
        try {
            //创建一个输出流对象
            out = new FileWriter(xmlfilename);
            //创建一个dom4j创建xml的对象
            XMLWriter writer = new XMLWriter(out, format);
            //调用write方法将doc文档写到指定路径
            writer.write(doc);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

    private static void cycleReadFileInfo(String currentPath, Element currentEle, boolean onlyVisible) {
//        System.out.println("cycleReadFileInfo:" + currentPath);
        List<Element> list = currentEle.elements("File");
        for (Element fileEle : list) {
            String filename = fileEle.attributeValue("name");
            if (UtilMusicFileInfo.isMusicFile(filename)) {
                System.out.println("fileEle:" + fileEle.attributeValue("name"));
                String fullfilename = currentPath + SPLIT_MAC + filename;
                System.out.println(fullfilename);
                File musicfile = new File(fullfilename);
                if (onlyVisible == false || onlyVisible && musicfile.isHidden() == false) {
                    //TODO:将文件的header和tag信息写入xml文件
                    if (fileEle.elements("AudioHeader").size() == 0 || fileEle.attributeValue("audioHeader").equals("error")) {
                        AudioHeader audioHeader = UtilMusicFileInfo.getHeader(musicfile);
                        if (audioHeader != null) {
                            System.out.println(audioHeader);
                            Element eleHeader = fileEle.addElement("AudioHeader");
                            eleHeader.addAttribute("Encoding", audioHeader.getEncodingType());
                            eleHeader.addAttribute("BitRate", audioHeader.getBitRate());
//                        eleHeader.addAttribute("BitRateNumber", audioHeader.getBitRateAsNumber() + "");
                            eleHeader.addAttribute("SampleRate", audioHeader.getSampleRate());
//                        eleHeader.addAttribute("SampleRateNumber", audioHeader.getSampleRateAsNumber() + "");
                            eleHeader.addAttribute("Format", audioHeader.getFormat());
                            eleHeader.addAttribute("Channels", audioHeader.getChannels());
                            eleHeader.addAttribute("VariableBitRate", audioHeader.isVariableBitRate() + "");
                            eleHeader.addAttribute("TrackLength", audioHeader.getTrackLength() + "");
                            fileEle.addAttribute("audioHeader", "ok");
                        } else {
                            fileEle.addAttribute("audioHeader", "error");
                        }
                    }
                    if (fileEle.elements("CommonTag").size() == 0 || fileEle.attributeValue("commonTag").equals("error")) {
                        CommonTag commonTag = UtilMusicFileInfo.getCommonTag(musicfile);
                        if (commonTag != null) {
                            System.out.println(commonTag);
                            if (commonTag.getLoadErrorCode() > 0) {
                                fileEle.addAttribute("commonTag", "error:" + commonTag.getLoadErrorMessage());
                            } else {
                                Element eleTag = fileEle.addElement("CommonTag");
                                eleTag.addAttribute("tagType", commonTag.getTagType());
                                eleTag.addAttribute("ALBUM", commonTag.getALBUM());
                                eleTag.addAttribute("ALBUMARTIST", commonTag.getALBUMARTIST());
                                eleTag.addAttribute("TITLE", commonTag.getTITLE());
                                eleTag.addAttribute("ARTIST", commonTag.getARTIST());
                                eleTag.addAttribute("TRACKNUMBER", commonTag.getTRACKNUMBER());
                                eleTag.addAttribute("TRACKTOTAL", commonTag.getTRACKTOTAL());
                                eleTag.addAttribute("DISCNUMBER", commonTag.getDISCNUMBER());
                                eleTag.addAttribute("DISCTOTAL", commonTag.getDISCTOTAL());
                                eleTag.addAttribute("DATE", commonTag.getDATE());
                                eleTag.addAttribute("GENRE", commonTag.getGENRE());
                                eleTag.addAttribute("COMMENT", commonTag.getCOMMENT());
                                eleTag.addAttribute("LYRICS", commonTag.getLYRICS());
                                eleTag.addAttribute("LANGUAGE", commonTag.getLANGUAGE());
                                eleTag.addAttribute("IMAGE", commonTag.getIMAGE() + "");
                                fileEle.addAttribute("commonTag", "ok");
                            }
                        } else {
                            fileEle.addAttribute("commonTag", "error");
                        }
                    }
                }
            }
        }
        for (Object obj : currentEle.elements("Path")) {
            Element elePath = (Element) obj;
            cycleReadFileInfo(currentPath + SPLIT_MAC + elePath.attributeValue("name"), elePath, onlyVisible);
        }
    }

    private static void updateFileWhenScan() {
        if (scanFileCount > 1000) {
//            OutputFormat format = OutputFormat.createPrettyPrint();
//            format.setEncoding("utf-8");
//            Writer out;
//            try {
//                //创建一个输出流对象
//                out = new FileWriter(xmlfilename);
//                //创建一个dom4j创建xml的对象
//                XMLWriter writer = new XMLWriter(out, format);
//                //调用write方法将doc文档写到指定路径
//                writer.write(doc);
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            scanFileCount = 0;
        }
    }

    public static DefaultMutableTreeNode createTreeByXml(String xmlpathname) {

        Document doc = null;
        try {
            doc = new SAXReader().read(new File(xmlpathname));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootEle = doc.getRootElement();
        System.out.println(rootEle);
        String rootPath = rootEle.attributeValue("path");

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootEle.getName());

        cycleCreateTreeByXml(rootEle, rootNode);
        return rootNode;
    }

    public static DefaultMutableTreeNode createTreeByFile(String xmlpathname) {

        Document doc = null;
        try {
            doc = new SAXReader().read(new File(xmlpathname));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootEle = doc.getRootElement();
        System.out.println(rootEle);
        String rootPath = rootEle.attributeValue("path");

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootEle.getName());

        cycleCreateTreeByFile(rootEle, rootNode);
        return rootNode;
    }

    private static void cycleCreateTreeByXml(Element currentEle, DefaultMutableTreeNode currentNode) {
        List<Element> listPath = currentEle.elements("Path");
        for (Element elePath : listPath) {
            DefaultMutableTreeNode pathNode = new DefaultMutableTreeNode(elePath.attributeValue("name"));
            currentNode.add(pathNode);
            cycleCreateTreeByXml(elePath, pathNode);
        }
        List<Element> listFile = currentEle.elements("File");
        for (Element eleFile : listFile) {
            DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(eleFile.attributeValue("name"));
            currentNode.add(fileNode);
        }
    }

    private static void cycleCreateTreeByFile(Element currentEle, DefaultMutableTreeNode currentNode) {
        List<Element> listPath = currentEle.elements("Path");
        for (Element elePath : listPath) {
            DefaultMutableTreeNode pathNode = new DefaultMutableTreeNode(elePath.attributeValue("name"));
            currentNode.add(pathNode);
            cycleCreateTreeByXml(elePath, pathNode);
        }
        List<Element> listFile = currentEle.elements("File");
        for (Element eleFile : listFile) {
            DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(eleFile.attributeValue("name"));
            currentNode.add(fileNode);
        }
    }

    public static void main(String[] args) {
        String path = "/Volumes/MusicP5T/标准文件夹/[TEST]";
        String xmlpath = "/Users/mac/Desktop/标准文件夹[TEST].xml";
        UtilDiskXml.createXml(path, xmlpath, true);
    }
}