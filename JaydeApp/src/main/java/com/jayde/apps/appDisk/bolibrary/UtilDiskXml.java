package com.jayde.apps.appDisk.bolibrary;

import com.jayde.util.diskutils.OnlyDirectory;
import com.jayde.util.diskutils.OnlyFile;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.CyclicBarrier;

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

    public static void createXml(String rootpathname, String xmlpathname) {
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

    public static void scanPaths(String rootpathname, String xmlpathname) {
        File fromPath = new File(rootpathname);
        String pathName = fromPath.getName();
        String toPath = xmlpathname + "/" + pathName + ".xml";
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("ROOT");
        root.addAttribute("name", pathName);
        root.addAttribute("path", fromPath.getAbsolutePath());
        cycleScanPath(fromPath, root);

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

    private static void cycleScanPath(File currentPath, Element parentEle) {
        System.out.println("cycleScanPath:" + currentPath.getName());
        Element currentEle = parentEle.addElement("Path");
        currentEle.addAttribute("name", currentPath.getName());
//        currentEle.addAttribute("fullPath", currentPath.getAbsolutePath());
        currentEle.addAttribute("scan", "0");
        currentEle.addAttribute("selfFilesCount", "");
        currentEle.addAttribute("selfPathsCount", "");
        currentEle.addAttribute("selfFilesSize", "");
        currentEle.addAttribute("allFilesCount", "");
        currentEle.addAttribute("allPathsCount", "");
        currentEle.addAttribute("allFilesSize", "");
        currentEle.addAttribute("attributes", "");
        File[] sonpaths = currentPath.listFiles(onlyDirectory);
        for (File sonpath : sonpaths) {
            cycleScanPath(sonpath, currentEle);
        }
    }

    public static void scanFiles(String rootpathname, String xmlpathname) {
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
            cycleScanFile(rootPath, elePath);
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

    private static void cycleScanFile(String currentPath, Element currentEle) {
        System.out.println("cycleScanFile:" + currentPath);
        String isScan = currentEle.attributeValue("scan");
        if (isScan.equals("0") || isScan.equals("")) {
            File path = new File(currentPath);
            File[] sonfiles = path.listFiles(onlyFile);
            long selfFilesSize = 0l;
            for (File sonfile : sonfiles) {
                Element eleFile = currentEle.addElement("File");
                eleFile.addAttribute("name", sonfile.getName());
                eleFile.addAttribute("size", sonfile.length() + "");
                eleFile.addAttribute("createDate", "");
                eleFile.addAttribute("modifyDate", sonfile.lastModified() + "");
                eleFile.addAttribute("attributes", "");
                selfFilesSize += sonfile.length();
            }
            currentEle.addAttribute("scan", "1");
            currentEle.addAttribute("selfFilesCount", sonfiles.length + "");
            currentEle.addAttribute("selfPathsCount", path.listFiles(onlyDirectory).length + "");
            currentEle.addAttribute("selfFilesSize", selfFilesSize + "");
        }
        for (Object obj : currentEle.elements("Path")) {
            Element elePath = (Element) obj;
            cycleScanFile(currentPath + SPLIT_MAC + elePath.attributeValue("name"), elePath);
        }
    }

    public static void main(String[] args) {
        String path = "/Volumes/MusicP5T/标准文件夹";
        String xmlpath = "/Users/mac/Desktop/标准文件夹.xml";
        UtilDiskXml.createXml(path, xmlpath);
    }
}
