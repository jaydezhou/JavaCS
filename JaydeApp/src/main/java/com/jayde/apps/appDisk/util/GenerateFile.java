package com.jayde.apps.appDisk.util;

import com.jayde.apps.appDisk.bo.BoDisk;
import lombok.extern.log4j.Log4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.util
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2018/10/29 下午3:27
 * @UpdateUser: The Modified user
 * @UpdateDate: 2018/10/29 下午3:27
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Log4j
public class GenerateFile {
    static OnlyFileFilter fileFilter = new OnlyFileFilter();
    static OnlyPathFilter pathFilter = new OnlyPathFilter();
    static String split = "";


    /**
     * 初始化文件夹信息到path.xml
     *
     * @param fromPath
     * @param toRootPath
     * @param os
     * @throws IOException
     */
    public static void generateFootToXmlFile(File fromPath, File toRootPath, String os,String diskformat,String block) throws IOException {
        Date start = new Date();
        switch (os) {
            case "win":
                split = "\\";
                break;
            case "mac":
                split = "/";
                break;
        }
        File toPath = new File(toRootPath.getAbsoluteFile() + split + fromPath.getName());
        toPath.mkdir();
        String toPathStr = toPath.getAbsoluteFile() + split;
        File toFile = new File(toPathStr + fromPath.getName() + ".xml");
        log.info(toFile.getAbsoluteFile());
        log.info(toFile.createNewFile());


        // 创建Document对象
        Document document = DocumentHelper.createDocument();
        // 创建根节点
        Element eleRoot = document.addElement("ROOT");

        generatePathToXmlFile(eleRoot, fromPath);

        eleRoot.addAttribute("Name", fromPath.getAbsolutePath());
        eleRoot.addAttribute("Rootpath", fromPath.getParentFile().getAbsolutePath());
        eleRoot.addAttribute("FreeSpace", String.valueOf(fromPath.getFreeSpace()));
        eleRoot.addAttribute("TotalSpace", String.valueOf(fromPath.getTotalSpace()));
        eleRoot.addAttribute("Format", diskformat);
        eleRoot.addAttribute("Block", block);

        // 创建输出格式(OutputFormat对象)
        OutputFormat format = OutputFormat.createPrettyPrint();

        ///设置输出文件的编码
//      format.setEncoding("GBK");

        // 创建XMLWriter对象
        XMLWriter writer = new XMLWriter(new FileOutputStream(toFile), format);
        //设置不自动进行转义
        writer.setEscapeText(false);
        // 生成XML文件
        writer.write(document);
        //关闭XMLWriter对象
        writer.close();
        Date end = new Date();

        log.info("-----------------------------");
        log.error("Start:" + start);
        log.error("End  ::" + end);
    }

    /**
     * 循环将文件夹信息写入path.xml
     *
     * @param parentEle
     * @param selfPath
     * @throws IOException
     */
    public static void generatePathToXmlFile(Element parentEle, File selfPath) throws IOException {

        log.info(selfPath.getAbsolutePath());

        String pathId = UUID.randomUUID().toString();

        Element selfEle = parentEle.addElement("P");
        selfEle.addAttribute("name", selfPath.getName());
        selfEle.addAttribute("UUID", pathId);
        selfEle.addAttribute("scan", "0");

        File[] paths = selfPath.listFiles(pathFilter);
        if(paths!=null) {
            selfEle.addAttribute("selfPaths", String.valueOf(paths.length));
            if (paths.length > 0) {
                for (File path : paths) {
                    generatePathToXmlFile(selfEle, path);
                }
            }
        }
        else {
            selfEle.addAttribute("selfPaths", "0");
        }
        return;
    }

    /**
     * 递归扫描path.xml文件中的path，调用生成file.xml方法
     *
     * @param fromPath
     * @param toRootPath
     * @throws IOException
     * @throws DocumentException
     */
    public static void scanPathInXmlFile(File fromPath, File toRootPath, String os) throws IOException, DocumentException {
        Date start = new Date();

        switch (os) {
            case "win":
                split = "\\";
                break;
            case "mac":
                split = "/";
                break;
        }
        String toPathStr = toRootPath.getAbsoluteFile() + split + fromPath.getName();
        File toPath = new File(toPathStr);

        File toFile = new File(toPathStr + split + fromPath.getName() + ".xml");

        if (toFile.exists() == false) {
            log.error("file:" + toFile.getAbsolutePath() + "    not exist");
            return;
        }
        toPathStr += split;
        SAXReader sax = new SAXReader();//创建一个SAXReader对象
        Document document = sax.read(toFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
        Element root = document.getRootElement();//获取根节点
        Stack<Element> stackEles = new Stack<>();
        List<Element> listEles0 = root.elements("P");
        for (Element ele : listEles0) {
            stackEles.push(ele);
        }

        while (stackEles.size() > 0) {
            Element eleInStack = stackEles.pop();

            if (eleInStack.attributeValue("scan").equals("0")) {
                log.info(stackEles.size() + ":" + eleInStack.attributeValue("name"));
                //扫描生成file的xml
                generateFileToXmlFile(eleInStack, toPathStr);
            }

            List<Element> list = eleInStack.elements("P");
            for (Element ele : list) {
                stackEles.push(ele);
            }
        }

        // 创建输出格式(OutputFormat对象)
        OutputFormat format = OutputFormat.createPrettyPrint();

        ///设置输出文件的编码
//      format.setEncoding("GBK");

        // 创建XMLWriter对象
        XMLWriter writer = new XMLWriter(new FileOutputStream(toFile), format);
        //设置不自动进行转义
        writer.setEscapeText(false);
        // 生成XML文件
        writer.write(document);
        //关闭XMLWriter对象
        writer.close();

        Date end = new Date();

        log.info("-----------------------------");
        log.error("Start:" + start);
        log.error("End  ::" + end);
    }

    /**
     * 循环将文件夹信息写入file.xml
     *
     * @param parentEle
     * @param toPathStr
     * @throws IOException
     */
    public static void generateFileToXmlFile(Element parentEle, String toPathStr) throws IOException {
//                selfEle.addAttribute("FreeSpace",String.valueOf(selfPath.getFreeSpace()));
//        selfEle.addAttribute("TotalSpace",String.valueOf(selfPath.getTotalSpace()));
//        selfEle.addAttribute("UsableSpace",String.valueOf(selfPath.getUsableSpace()));

//        int i = 0;
//        log.info(parentEle);
        log.error("generateFileToXmlFile:"+parentEle);
        String pathStr = parentEle.attributeValue("name");
        Element upEle = parentEle.getParent();
        while (upEle.getName().equals("P")) {
            pathStr = upEle.attributeValue("name") + split + pathStr;
            upEle = upEle.getParent();
        }
        pathStr = upEle.attributeValue("Rootpath") + split + pathStr + split;
//        log.info(pathStr);
        File selfPath = new File(pathStr);
        File[] files = selfPath.listFiles(fileFilter);
//        parentEle.addAttribute("selfFiles", String.valueOf(files.length));
        if (files == null || files.length == 0) {
            parentEle.addAttribute("selfFiles", "0");
        }
         else {
            log.warn("ok");
            parentEle.addAttribute("selfFiles", String.valueOf(files.length));
            File xmlFile = new File(toPathStr + parentEle.attributeValue("UUID") + ".xml");
            log.info(xmlFile.getAbsolutePath());
            Document document = DocumentHelper.createDocument();
            Element eleRoot = document.addElement("ROOT");
            for (File file : files) {
                Element eleFile = eleRoot.addElement("F");
                eleFile.addAttribute("name", file.getName());
                String RWE = "";
                if (file.canRead()) {
                    RWE = "1";
                } else {
                    RWE = "0";
                }
                if (file.canWrite()) {
                    RWE += "1";
                } else {
                    RWE += "0";
                }
                if (file.canExecute()) {
                    RWE += "1";
                } else {
                    RWE += "0";
                }
                eleFile.addAttribute("RWE", RWE);
                eleFile.addAttribute("length", String.valueOf(file.length()));
            }

            // 创建输出格式(OutputFormat对象)
            OutputFormat format = OutputFormat.createPrettyPrint();

            ///设置输出文件的编码
            //format.setEncoding("GBK");

            // 创建XMLWriter对象
            XMLWriter writer = new XMLWriter(new FileOutputStream(xmlFile), format);
            //设置不自动进行转义
            writer.setEscapeText(false);
            // 生成XML文件
            writer.write(document);
            //关闭XMLWriter对象
            writer.close();
        }
    }


    public static void countXmlFile(File fromPath, File toRootPath, String os)

    {

    }

    public static void main(String[] args) throws DocumentException {
//        File diskpath = new File("/Volumes/ps_mac");
        File diskpath = new File("/Volumes/SS4");
//        File diskpath = new File("/Volumes/Passport_MP3");
        File filepath = new File("/Users/mac/Documents/diskpath");
        try {
            GenerateFile.generateFootToXmlFile(diskpath, filepath, "mac","ntfs","0");
//            GenerateFile.scanPathInXmlFile(diskpath, filepath, "mac");
//            GenerateFile.generatePathToXmlFile(null,);
        } catch (IOException ioe) {

        }
    }
}
