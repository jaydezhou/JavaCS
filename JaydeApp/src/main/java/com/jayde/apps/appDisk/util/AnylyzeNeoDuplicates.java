package com.jayde.apps.appDisk.util;

import com.hankcs.hanlp.corpus.dictionary.TMDictionaryMaker;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.util
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2018/11/18 下午8:30
 * @UpdateUser: The Modified user
 * @UpdateDate: 2018/11/18 下午8:30
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Log4j
public class AnylyzeNeoDuplicates {
    String rootPathStr = "/Users/mac/Documents/diskpath/NeoFinder导出";
    static long size1G = 1000000000;
    Map<DuplicateKey, List<DuplicateItem>> bigFolderSet = new ConcurrentHashMap<>();

    public AnylyzeNeoDuplicates() {
        step1_readPath();
//        step4_showMap();
        step5_sortDuplicateFolder();
    }

    public void step1_readPath() {
        Date start = new Date();
        log.info("start");

        File path = new File(rootPathStr);
        File[] xmlFiles = path.listFiles();
        for (File xmlFile : xmlFiles) {
            if (xmlFile.getName().endsWith("(export).xml")) {
                log.info(xmlFile.getName());
                step2_readFile(xmlFile);
            }
        }

        Date end = new Date();
        log.warn("Start:" + start);
        log.warn("End:" + end);
    }

    public void step2_readFile(File file) {
        try {
            SAXReader sax = new SAXReader();//创建一个SAXReader对象
            Document document = sax.read(file);//获取document对象,如果文档无节点，则会抛出Exception提前结束
            Element root = document.getRootElement();//获取根节点
//            log.info(root);
            String catalogName = root.attributeValue("name");
            List<Element> elesFolders = root.elements("Folder");
            for (Element ele : elesFolders) {
                step3_readFolder(catalogName, ele);
            }
        } catch (DocumentException e) {

        }
    }

    public void step3_readFolder(String catalogName, Element ele) {

        long sonFilesSize = Long.valueOf(ele.attributeValue("sonFilesSize"));
//        int sonFilesCount = Integer.valueOf(ele.attributeValue("sonFilesCount"));
        if (sonFilesSize > size1G) {
//            log.info("Step3");
//            FolderKey folderKey = new FolderKey(catalogName, ele.attributeValue("itemID"));
            DuplicateItem duplicateItem = new DuplicateItem(catalogName, ele);

            Long keysize = Long.valueOf(sonFilesSize);
            String keyname = ele.attributeValue("name");
            DuplicateKey key = new DuplicateKey();
            key.setName(keyname);
            key.setSonFilesSize(keysize);

            List<DuplicateItem> list;
            if (bigFolderSet.containsKey(key)) {
                list = bigFolderSet.get(key);
                list.add(duplicateItem);
            } else {
                list = new ArrayList<>();
                list.add(duplicateItem);
                bigFolderSet.put(key, list);
            }
            List<Element> eles = ele.elements("Folder");
            for (Element ele2 : eles) {
                step3_readFolder(catalogName, ele2);
            }
        }
    }

    public void step4_showMap() {
        log.info("map size:" + bigFolderSet.size());
    }

    public void step5_sortDuplicateFolder() {
        //先排除无重复的文件夹
        for (Map.Entry<DuplicateKey, List<DuplicateItem>> entry : bigFolderSet.entrySet()) {
            List<DuplicateItem> list = entry.getValue();
            if (list.size() == 1) {
                bigFolderSet.remove(entry.getKey());
            }
        }

        //再排除父文件夹已存在的
//        for (Map.Entry<Long, List<DuplicateItem>> entry1 : bigFolderSet.entrySet()) {
//            boolean e = false;
//            List<DuplicateItem> list = entry1.getValue();
//            Long key = entry1.getKey();
//            DuplicateItem item1 = list.get(0);
//
//            for (Map.Entry<Long, List<DuplicateItem>> entry2 : bigFolderSet.entrySet()) {
//                if (entry2.getKey() != key) {
//                    List<DuplicateItem> list2 = entry2.getValue();
//                    for (DuplicateItem item2 : list2) {
//                        if (item2.getCatalogName().equals(item1.getCatalogName()) && item2.getItemID().equals(item1.getParentID())) {
//                            e = true;
//                        }
//                    }
//                }
//            }
//
//            if(e){
//                bigFolderSet.remove(entry1);
//            }
//        }

        Set<DuplicateKey> keySet = bigFolderSet.keySet();
        Object[] keyArray = keySet.toArray();
        for (int i = 0; i < keyArray.length - 1; i++) {
            for (int j = i + 1; j < keyArray.length; j++) {
                DuplicateKey DuplicateKeyi = (DuplicateKey) keyArray[i];
                DuplicateKey DuplicateKeyj = (DuplicateKey) keyArray[j];
                long li = DuplicateKeyi.getSonFilesSize();
                long lj = DuplicateKeyj.getSonFilesSize();

                if (li < lj) {
                    DuplicateKey tempkey = new DuplicateKey();
                    tempkey = (DuplicateKey) keyArray[i];
                    keyArray[i] = keyArray[j];
                    keyArray[j] = tempkey;
                }
            }
        }
        //按文件夹大小输出
        for (Object keyobj : keyArray) {
//            for (Map.Entry<Long, List<DuplicateItem>> entry1 : bigFolderSet.entrySet()) {
            DuplicateKey key = (DuplicateKey) keyobj;
            List<DuplicateItem> list = bigFolderSet.get(key);
            System.out.println(key.getSonFilesSize() + "," + key.getSonFilesSize() / 1000000000);
            for (DuplicateItem item : list) {
                System.out.println(" ," + item.getCatalogName() + "," + item.getName());
            }
        }
//        }

        log.error(bigFolderSet.size());
    }

    public static void main(String[] args) {
        AnylyzeNeoDuplicates anylyzeNeoDuplicates = new AnylyzeNeoDuplicates();
    }

    @Data
    class FolderKey {
        String catalogName;
        String folderItemId;

        public FolderKey(String c, String f) {
            catalogName = c;
            folderItemId = f;
        }
    }

    @Data
    class DuplicateItem {
        String catalogName;
        String itemID;
        String parentID;
        String name;
        long sonFilesSize;
        int sonFilesCount;
        int sonFoldersCount;

        public DuplicateItem(String c, Element ele) {
            catalogName = c;
            itemID = ele.attributeValue("itemID");
            parentID = ele.attributeValue("parentID");
            name = ele.attributeValue("name");
            sonFilesSize = Long.valueOf(ele.attributeValue("sonFilesSize"));
            sonFilesCount = Integer.valueOf(ele.attributeValue("sonFilesCount"));
            sonFoldersCount = Integer.valueOf(ele.attributeValue("sonFoldersCount"));
        }
    }

    @Data
    class DuplicateKey {
        String name;
        long sonFilesSize;
//        int sonFilesCount;
//        int sonFoldersCount;

    }
}
