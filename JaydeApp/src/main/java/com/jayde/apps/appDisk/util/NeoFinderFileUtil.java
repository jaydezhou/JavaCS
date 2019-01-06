package com.jayde.apps.appDisk.util;

import lombok.Data;
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
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.util
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2018/11/14 下午8:11
 * @UpdateUser: The Modified user
 * @UpdateDate: 2018/11/14 下午8:11
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Log4j
public class NeoFinderFileUtil {
    String rootPathStr = "/Users/mac/Documents/diskpath/NeoFinder导出";
    String neoFileStr = "ST5T【音频】";
    String split = "/";
    Map<Integer, String> blankMap = new HashMap<>();
    int iMaxLevel = 0;
    Element root = null;
    List<Element> listItemEles = null;
    Map<String, ItemFolder> folderMap = new ConcurrentHashMap<>();
    //ConcurrentModificationException异常
    //　　1）在使用iterator迭代的时候使用synchronized或者Lock进行同步；
    //　　2）使用并发容器CopyOnWriteArrayList代替ArrayList和Vector。
    List<ItemFile> fileList = new ArrayList<>();
    ItemFolder rootItemFolder = new ItemFolder();

    public void sortNeo() throws IOException, DocumentException {

        Date start = new Date();
        log.info("start");
        File neoFile = new File(rootPathStr + split + neoFileStr + ".xml");
        if (neoFile.exists() == false) {
            log.error("file:" + neoFile.getAbsolutePath() + "    not exist");
            return;
        }
        SAXReader sax = new SAXReader();//创建一个SAXReader对象
        Document document = sax.read(neoFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
        root = document.getRootElement();//获取根节点

        step1_showCatalog();
        step2_iniList();

        step3_doFilesTree();
        step4_doFoldersTree();
        step5_setLvel();
//        step4_doFoldersTree();
        step8_showTree(rootItemFolder, 0);
        step9_export();
        Date end = new Date();
        log.warn("Start:" + start);
        log.warn("End:" + end);

    }

    public void step1_showCatalog() {
        log.info(root);
        Element eleName = root.element("name");
        log.info(eleName.getText());

        Element eleSize = root.element("size");
        log.info(eleSize.getText());

        Element eleUnused = root.element("unused");
        log.info(eleUnused.getText());

        Element eleFiles = root.element("files");
        log.info(eleFiles.getText());

        Element eleFolders = root.element("folders");
        log.info(eleFolders.getText());

        Element eleSerial = root.element("serial");
        log.info(eleSerial.getText());

        Element eleCrDate = root.element("crDate");
        log.info(eleCrDate.getText());

        Element eleModDate = root.element("modDate");
        log.info(eleModDate.getText());

        log.warn("step1 end");
    }

    public void step2_iniList() {
        listItemEles = root.elements("Item");
        log.info(listItemEles.size());
        folderMap.put(rootItemFolder.getItemID(), rootItemFolder);
        for (Element ele : listItemEles) {
            Element eleType = ele.element("type");
            switch (eleType.getTextTrim()) {
                case "Folder":
                    ItemFolder itemFolder = new ItemFolder(ele);
                    folderMap.put(itemFolder.getItemID(), itemFolder);
                    break;
                case "File":
                    ItemFile itemFile = new ItemFile(ele);
                    fileList.add(itemFile);
                    break;
            }
        }
        log.warn("folderList:" + folderMap.size());
        log.warn("fileList:" + fileList.size());

        log.warn("step2 end");
    }


    public void step3_doFilesTree() {
        for (ItemFile itemFile : fileList) {
            ItemFolder itemFolder = folderMap.get(itemFile.getParentID());
            if (itemFolder != null) {
//                itemFolder.getSonFileItems().add(itemFile);
//                itemFile.setParentFolderItem(itemFolder);
                itemFolder.addFile(itemFile);
            }
        }
        log.warn("step3 end");
    }

    public void step4_doFoldersTree() {
        for (Map.Entry<String, ItemFolder> entry : folderMap.entrySet()) {
            ItemFolder itemFolder = entry.getValue();
            if (itemFolder != rootItemFolder) {
                ItemFolder parentItemFolder = folderMap.get(itemFolder.getParentID());
                parentItemFolder.addFolder(itemFolder);
            }
        }


        for (Map.Entry<String, ItemFolder> entry : folderMap.entrySet()) {
            ItemFolder itemFolder = entry.getValue();

            if (itemFolder != rootItemFolder) {
                ItemFolder upItemFolder = itemFolder.getParentFolderItem();
                int iLevel = 1;
                while (upItemFolder != rootItemFolder) {
                    iLevel++;
                    upItemFolder = upItemFolder.getParentFolderItem();
                }
                itemFolder.setLevel(iLevel);
                if (iLevel > iMaxLevel) {
                    iMaxLevel = iLevel;
                }
            }
        }
        log.info("iMaxLevel:" + iMaxLevel);
    }

    public void step5_setLvel() {
        for (int iLevel = iMaxLevel; iLevel >= 0; iLevel--) {
            for (Map.Entry<String, ItemFolder> entry : folderMap.entrySet()) {
                ItemFolder itemFolder = entry.getValue();
                if (itemFolder.getLevel() == iLevel) {
                    int iSonFolderCount = 0;
                    int iSonFileCount = itemFolder.getSelfFilesCount();
                    long lSonFilesSize = itemFolder.getSelfFilesSize();
                    for (ItemFolder sonFolder : itemFolder.getSonFolderItems()) {
                        iSonFolderCount += sonFolder.getSonFoldersCount() + 1;
                        iSonFileCount += sonFolder.getSonFilesCount();
                        lSonFilesSize += sonFolder.getSonFilesSize();
                    }
                    itemFolder.setSonFoldersCount(iSonFolderCount);
                    itemFolder.setSonFilesCount(iSonFileCount);
                    itemFolder.setSonFilesSize(lSonFilesSize);
                    log.info("第" + iLevel + "级:" + itemFolder.toString());
                }
            }
        }
    }

    public void step5_doFoldersTree() {

        Stack<ItemFolder> stackFolders = new Stack<>();
        stackFolders.push(rootItemFolder);
        while (stackFolders.size() > 0) {
            ItemFolder itemFolderPop = stackFolders.pop();
            String parentItemId = itemFolderPop.getItemID();
            for (Map.Entry<String, ItemFolder> entry : folderMap.entrySet()) {
                ItemFolder itemFolder = entry.getValue();
                if (itemFolder.getParentID().equals(parentItemId)) {
//                    itemFolderPop.getSonFolderItems().add(itemFolder);
//                    itemFolder.setParentFolderItem(itemFolderPop);
                    itemFolderPop.addFolder(itemFolder);
                    stackFolders.push(itemFolder);
                    folderMap.remove(entry.getKey());
                }
            }
        }
        log.warn("step4 end");
    }

    public void step8_showTree(ItemFolder itemFolder, int level) {
        Integer key = Integer.valueOf(level);
        if (blankMap.containsKey(key) == false) {
            String blank = "";
            for (int i = 0; i < level; i++) {
                blank += " ";
            }
            blankMap.put(key, blank);
        }
        if (itemFolder.getSonFilesCount() > 0) {
            log.info(blankMap.get(key) + itemFolder);
        }
        for (ItemFolder sonItemFolder : itemFolder.getSonFolderItems()) {
            step8_showTree(sonItemFolder, level + 2);
        }
    }

    public void step9_export() {
        File exportFile = new File(rootPathStr + split + neoFileStr + "(export).xml");
//        exportFile.deleteOnExit();
        try {
            exportFile.createNewFile();
            log.info(exportFile.getAbsolutePath());
            Document document = DocumentHelper.createDocument();
            Element eleRoot = document.addElement("ROOT");

            eleRoot.addAttribute("name", root.element("name").getTextTrim());
            eleRoot.addAttribute("size", root.element("size").getTextTrim());
            eleRoot.addAttribute("unused", root.element("unused").getTextTrim());
            eleRoot.addAttribute("folders", root.element("folders").getTextTrim());
            eleRoot.addAttribute("files", root.element("files").getTextTrim());
            eleRoot.addAttribute("serial", root.element("serial").getTextTrim());
            eleRoot.addAttribute("crDate", root.element("crDate").getTextTrim());
            eleRoot.addAttribute("modDate", root.element("modDate").getTextTrim());
            step9_1_addFolders(eleRoot, rootItemFolder);

            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(new FileOutputStream(exportFile), format);
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void step9_1_addFolders(Element eleParentFolder, ItemFolder itemFolder) {
        Element eleThisFolder = eleParentFolder.addElement("Folder");

        eleThisFolder.addAttribute("itemID", itemFolder.getItemID());
        eleThisFolder.addAttribute("parentID", itemFolder.getParentID());
        eleThisFolder.addAttribute("name", itemFolder.getName());
        eleThisFolder.addAttribute("type", itemFolder.getType());
        eleThisFolder.addAttribute("level", String.valueOf(itemFolder.getLevel()));
        eleThisFolder.addAttribute("size", itemFolder.getSize());
        eleThisFolder.addAttribute("crDate", itemFolder.getCrDate());
        eleThisFolder.addAttribute("modDate", itemFolder.getModDate());
        eleThisFolder.addAttribute("selfFilesSize", String.valueOf(itemFolder.getSelfFilesSize()));
        eleThisFolder.addAttribute("selfFilesCount", String.valueOf(itemFolder.getSelfFilesCount()));
        eleThisFolder.addAttribute("selfFoldersCount", String.valueOf(itemFolder.getSelfFoldersCount()));
        eleThisFolder.addAttribute("sonFilesSize", String.valueOf(itemFolder.getSonFilesSize()));
        eleThisFolder.addAttribute("sonFilesCount", String.valueOf(itemFolder.getSonFilesCount()));
        eleThisFolder.addAttribute("sonFoldersCount", String.valueOf(itemFolder.getSonFoldersCount()));
//        for (ItemFile sonFile : itemFolder.getSonFileItems()) {
//            Element eleSonFile = eleThisFolder.addElement("File");
//            eleSonFile.addAttribute("itemID", sonFile.getItemID());
//            eleSonFile.addAttribute("parentID", sonFile.getParentID());
//            eleSonFile.addAttribute("name", sonFile.getName());
//            eleSonFile.addAttribute("size", sonFile.getSize());
//            eleSonFile.addAttribute("crDate", sonFile.getCrDate());
//            eleSonFile.addAttribute("modDate", sonFile.getModDate());
//        }
        for (ItemFolder sonFolder : itemFolder.getSonFolderItems()) {
            step9_1_addFolders(eleThisFolder, sonFolder);
        }
    }


    public static void main(String[] args) {
        NeoFinderFileUtil neoFinderFileUtil = new NeoFinderFileUtil();
        try {
            neoFinderFileUtil.sortNeo();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Data
    class ItemFolder {
        String itemID;
        String parentID;
        String name;
        String type;
        String size;
        String crDate;
        String modDate;
        ItemFolder parentFolderItem;
        long selfFilesSize;
        int selfFilesCount;
        int selfFoldersCount;
        long sonFilesSize;
        int sonFilesCount;
        int sonFoldersCount;
        int level;

        List<ItemFolder> sonFolderItems = new ArrayList<>();
        List<ItemFile> sonFileItems = new ArrayList<>();

        public ItemFolder(Element ele) {
            itemID = ele.element("itemID").getTextTrim();
            parentID = ele.element("parentID").getTextTrim();
            name = ele.element("name").getTextTrim();
            type = ele.element("type").getTextTrim();
            size = ele.element("size").getTextTrim();
            crDate = ele.element("crDate").getTextTrim();
            modDate = ele.element("modDate").getTextTrim();
        }

        public ItemFolder() {
            itemID = "0";
            parentID = "-1";
            name = "ROOT";
            type = "Folder";
            size = "0";
            crDate = "";
            modDate = "";
            level = 0;
        }

        public void addFolder(ItemFolder itemFolder) {

//                    itemFolderPop.getSonFolderItems().add(itemFolder);
//                    itemFolder.setParentFolderItem(itemFolderPop);
            sonFolderItems.add(itemFolder);
            itemFolder.setParentFolderItem(this);
            selfFoldersCount++;
        }

        public void addFile(ItemFile itemFile) {

//                itemFolder.getSonFileItems().add(itemFile);
//                itemFile.setParentFolderItem(itemFolder);
            Long fileSize = Long.valueOf(itemFile.getSize());
            sonFileItems.add(itemFile);
            selfFilesCount++;
            selfFilesSize += fileSize;
            itemFile.setParentFolderItem(this);
            ItemFolder up = parentFolderItem;
            while (up != null) {
                up.setSonFilesCount(up.getSonFilesCount() + 1);
                up.setSonFilesSize(up.getSonFilesSize() + fileSize);
                up = up.getParentFolderItem();
            }
        }

        @Override
        public String toString() {
            return name + "    [Self]Path(" + selfFoldersCount + ")  File(" + selfFilesCount + ")【" + toSizeStr(selfFilesSize) + "】  [Son]Path(" + sonFoldersCount + ")File(" + sonFilesCount + ")【" + toSizeStr(sonFilesSize) + "】";
//            return name + selfFilesCount + "(" + toSizeStr(selfFilesSize) + ")   " + sonFilesCount + "(" + toSizeStr(sonFilesSize) + ")";
        }

        public String toSizeStr(Long longSize) {
            if (longSize >= g) {
                return String.format("%.2f", longSize / g) + "g";
            }
            if (longSize >= m) {
                return String.format("%.2f", longSize / m) + "m";
            }
            if (longSize >= k) {
                return String.format("%.2f", longSize / k) + "k";
            }
            if (longSize >= b) {
                return String.format("%.2f", longSize / k) + "k";
            }
            return "";
        }

        long b = 0;
        float k = 1024f;
        float m = 1048576l;
        float g = 1073741824l;
    }

    @Data
    class ItemFile {
        String itemID;
        String parentID;
        String name;
        String type;
        String size;
        String crDate;
        String modDate;
        ItemFolder parentFolderItem;


        public ItemFile(Element ele) {
            itemID = ele.element("itemID").getTextTrim();
            parentID = ele.element("parentID").getTextTrim();
            name = ele.element("name").getTextTrim();
            type = ele.element("type").getTextTrim();
            size = ele.element("size").getTextTrim();
            crDate = ele.element("crDate").getTextTrim();
            modDate = ele.element("modDate").getTextTrim();
        }
    }
}
