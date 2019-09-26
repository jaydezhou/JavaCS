package com.jayde.apps.appDisk.bodisk;

import com.jayde.apps.appDisk.util.OnlyFileFilter;
import com.jayde.apps.appDisk.util.OnlyPathFilter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.Collator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

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

    /**
     * @param currentPath
     * @return
     */
    public static BoFolder createBoFolder(File currentPath) {
        BoFolder currentFolder = new BoFolder(currentPath);
        long selfFilesCount = 0l;
        long selfFolderCount = 0l;
        long selfFilesSize = 0l;
        long allFilesCount = 0l;
        long allFolderCount = 0l;
        long allFilesSize = 0l;


        File[] listFiles = currentPath.listFiles(onlyFileFilter);
        listFiles = sortFileArray(listFiles);
        for (File sonFile : listFiles) {
            BoFile sonBoFile = new BoFile(sonFile);
            selfFilesCount++;
            selfFilesSize += sonBoFile.getSize();

            System.out.println(sonBoFile + ":   " + sonBoFile.getFileType());
            switch (sonBoFile.getFileType()) {
                case BoFile.FILE_TYPE_11_MUSIC_SONG:
                    BoMusicSongFile songFile = new BoMusicSongFile(sonFile);
                    System.out.println("Song:" + sonFile);
                    songFile.setParentFolder(currentFolder);
                    currentFolder.getListSonFile().add(songFile);
                    break;
                default:
                    sonBoFile.setParentFolder(currentFolder);
                    currentFolder.getListSonFile().add(sonBoFile);
                    break;
            }
        }
        allFilesCount = selfFilesCount;
        allFilesSize = selfFilesSize;
//        allFolderCount = selfFolderCount;

        File[] listPaths = currentPath.listFiles(onlyPathFilter);
        listPaths = sortFileArray(listPaths);
        for (File sonPath : listPaths) {
            BoFolder sonBoFolder = createBoFolder(sonPath);
            sonBoFolder.setParentFolder(currentFolder);
            currentFolder.getListSonFolder().add(sonBoFolder);
            selfFolderCount++;
            allFilesCount += sonBoFolder.getAllFilesCount();
            allFilesSize += sonBoFolder.getAllFilesSize();
            allFolderCount += sonBoFolder.getAllFolderCount() + 1;
        }

        currentFolder.setSelfFilesCount(selfFilesCount);
        currentFolder.setSelfFilesSize(selfFilesSize);
        currentFolder.setSelfFolderCount(selfFolderCount);
        currentFolder.setAllFilesCount(allFilesCount);
        currentFolder.setAllFilesSize(allFilesSize);
        currentFolder.setAllFolderCount(allFolderCount);

        return currentFolder;
    }

    /**
     * @param rootBoFolder
     * @param fromFullPath
     * @param fullXmlPath
     * @return
     */
    public static Element exportXml(BoFolder rootBoFolder, String fromFullPath, String fullXmlPath) {
        File fromPath = new File(fromFullPath);
        String pathName = fromPath.getName();
        String toPath = fullXmlPath + "/" + pathName + ".xml";

        //创建一个xml文档
        Document doc = DocumentHelper.createDocument();
        //向xml文件中添加注释
        doc.addComment("这里是注释");
        //创建一个名为students的节点，因为是第一个创建，所以是根节点,再通过doc创建一个则会报错。
        Element root = doc.addElement("ROOT");
        root = rootBoFolder.toElement(root);
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
        return root;
    }

    /**
     * @param str
     * @return
     */
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

    /**
     * @param inputFileName
     * @return
     */
    public static String getUpperSuffix(String inputFileName) {
        if (inputFileName.contains(".") == false) {
            return "";
        }
        String[] subs = inputFileName.split("\\.");
        String suffix = subs[subs.length - 1].toUpperCase();
        return suffix.toUpperCase();
    }

    /**
     * 从XML文件中读入文件夹信息，返回根节点
     *
     * @param inputFile
     * @return
     */
    public static BoFolder getRootBoFileByXml(File inputFile) {
        Document doc = null;
        try {
            doc = new SAXReader().read(inputFile);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootEle = doc.getRootElement();

        BoFolder boRootFolder = null;
        List<Element> listEleFolder = rootEle.elements("P");
        if (listEleFolder.size() == 1) {
            Element eleFolder = listEleFolder.get(0);
            int filetype = Integer.parseInt(eleFolder.attributeValue("filetype"));
            //TODO:没写完
            switch (filetype) {
                case AbstractBoFile.FILETYPE_FOLDER_BOFOLDER: {
                    boRootFolder = new BoFolder(eleFolder);
                    boRootFolder = cycleGetRootBoFileByXml(eleFolder, boRootFolder);
                    break;
                }
                case AbstractBoFile.FILETYPE_FOLDER_BONORMALFOLDER: {
                    break;
                }
                case AbstractBoFile.FILETYPE_FOLDER_BOSETFOLDER: {
                    break;
                }
            }
        }
        return boRootFolder;
    }

    /**
     * 循环读取XML，生成每级的BoFolder和BoFile
     *
     * @param parentEleFolder
     * @param parentBoFolder
     * @return
     */
    private static BoFolder cycleGetRootBoFileByXml(Element parentEleFolder, BoFolder parentBoFolder) {
        List<Element> listEleFile = parentEleFolder.elements("F");
        for (Element eleFile : listEleFile) {
            int filetype = Integer.parseInt(eleFile.attributeValue("filetype"));
            BoFile boFile = null;
            //TODO:没写完
            switch (filetype) {
                case AbstractBoFile.FILETYPE_FILE_BOFILE: {
                    boFile = new BoFile(eleFile);
                    break;
                }
                case BoFile.FILE_TYPE_11_MUSIC_SONG:
                    boFile = new BoMusicSongFile(eleFile);
                    break;
            }
            boFile.setParentFolder(parentBoFolder);
            parentBoFolder.getListSonFile().add(boFile);
        }

        List<Element> listEleFolder = parentEleFolder.elements("P");
        for (Element eleFolder : listEleFolder) {
            int filetype = Integer.parseInt(eleFolder.attributeValue("filetype"));
            switch (filetype) {
                case AbstractBoFile.FILETYPE_FOLDER_BOFOLDER: {
                    BoFolder boFolder = new BoFolder(eleFolder);
                    boFolder = cycleGetRootBoFileByXml(eleFolder, boFolder);
                    boFolder.setParentFolder(parentBoFolder);
                    parentBoFolder.getListSonFolder().add(boFolder);
                    break;
                }
                case AbstractBoFile.FILETYPE_FOLDER_BONORMALFOLDER: {
                    break;
                }
                case AbstractBoFile.FILETYPE_FOLDER_BOSETFOLDER: {
                    break;
                }
            }
        }

        return parentBoFolder;
    }

    /**
     * 对文件数组根据文件名进行排序
     * 调用了地区信息，对中文排序有改善
     *
     * @param inputFiles
     * @return
     */
    public static File[] sortFileArray(File[] inputFiles) {
        File tempFile = null;
        for (int i = 0; i < inputFiles.length - 1; i++) {
            String leftName = inputFiles[i].getName();
            for (int j = i + 1; j < inputFiles.length; j++) {
                if (Collator.getInstance(Locale.CHINESE).compare(leftName, inputFiles[j].getName()) > 0) {
                    tempFile = inputFiles[i];
                    inputFiles[i] = inputFiles[j];
                    inputFiles[j] = tempFile;
                    leftName = inputFiles[i].getName();
                }
            }
        }
        return inputFiles;
    }

    /**
     * 根据输入的xml文件，返回JTree的模型
     *
     * @param inputFile
     * @return
     */
    public static TreeModel createTreeByXml(File inputFile) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("ROOT");
        BoFolder rootBoFolder = getRootBoFileByXml(inputFile);
        cycleTreeByXml(rootNode, rootBoFolder);

        return new DefaultTreeModel(rootNode);
    }

    /**
     * 循环BoFolder，来生成树上每个节点
     *
     * @param parentNode
     * @param currentBoFolder
     */
    private static void cycleTreeByXml(DefaultMutableTreeNode parentNode, BoFolder currentBoFolder) {
        DefaultMutableTreeNode currentNode = null;

        switch (currentBoFolder.getFileType()) {
            case AbstractBoFile.FILETYPE_FOLDER_BOFOLDER: {
                currentNode = new DefaultMutableTreeNode(currentBoFolder);
                parentNode.add(currentNode);
                break;
            }
            case AbstractBoFile.FILETYPE_FOLDER_BONORMALFOLDER: {
                break;
            }
            case AbstractBoFile.FILETYPE_FOLDER_BOSETFOLDER: {
                break;
            }
        }

        for (BoFile sonFile : currentBoFolder.getListSonFile()) {
            switch (sonFile.getFileType()) {
                case AbstractBoFile.FILETYPE_FILE_BOMUSICSONGFILE: {
                    BoMusicSongFile boMusicSongFile = (BoMusicSongFile) sonFile;
                    DefaultMutableTreeNode sonFileNode = new DefaultMutableTreeNode(boMusicSongFile);
                    currentNode.add(sonFileNode);
                    break;
                }
                case AbstractBoFile.FILETYPE_FILE_BOSETFILE: {
                    break;
                }
                case AbstractBoFile.FILETYPE_FILE_BONORMALFILE: {
                    break;
                }
                case AbstractBoFile.FILETYPE_FILE_BOFILE: {
                    DefaultMutableTreeNode sonFileNode = new DefaultMutableTreeNode(sonFile);
                    currentNode.add(sonFileNode);
                    break;
                }
            }

        }

        for (BoFolder sonBoFolder : currentBoFolder.getListSonFolder()) {
            cycleTreeByXml(currentNode, sonBoFolder);
        }
    }

    public static void main(String[] args) {
        String rootPath = "/Users/mac/Desktop/标准文件夹[TEST]";
        String toPath = "/Users/mac/Desktop";

//        String rootPath = "/Volumes/MusicP4T/标准文件夹[MusicP4T]";
        BoFolder rootFolder = UtilBoFile.createBoFolder(new File(rootPath));
        System.out.println(rootFolder);
        UtilBoFile.exportXml(rootFolder, rootPath, toPath);

        System.out.println(LocalDateTime.now());

//        String rootPath = "/Users/mac/Desktop/5.1dts/";
//失败        File testFile = new File(rootPath + "(01) [Various Artists] Anonymus - Romance.ape");
//成功        File testFile = new File(rootPath + "02 - Yin Cheng-zong - Autumn Moon Over The Placid West Lake.flac");
//成功        File testFile = new File(rootPath + "04. 天黑黑.flac");
//        File testFile = new File(rootPath + "阿杜-放手.mp3");
//        try {
//            AudioFile f = AudioFileIO.read(testFile);
//            AudioHeader audioHeader = f.getAudioHeader();
//            System.out.println("----------------audioHeader-----------------");
//            System.out.println(audioHeader);
//            Tag tag = f.getTag();
//            System.out.println("----------------tag-----------------");
//            System.out.println(tag);
//        } catch (Exception e) {
//            System.out.println(e.getLocalizedMessage());
//        }

    }

    public static boolean createLibrary(BoFolder libraryBoFolder) {
        BoSetFolder boSetFolder = (BoSetFolder) libraryBoFolder;
        boSetFolder.setLevel(0);
        return true;
    }

}
