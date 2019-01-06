package com.jayde.apps.appDisk.util;

import lombok.extern.log4j.Log4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.util
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2018/11/19 下午5:25
 * @UpdateUser: The Modified user
 * @UpdateDate: 2018/11/19 下午5:25
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Log4j
public class AnalyzeDiskUsed {
    String rootPathStr = "/Users/mac/Documents/diskpath/NeoFinder导出";
    String[] usedPaths = {
            "标准文件夹-【音频】-朗诵",
            "标准文件夹-【音频】-讲坛",
            "标准文件夹-【音频】-评书",
            "标准文件夹-【音频】-相声",
            "标准文件夹-【音频】-读物",
            "标准文件夹-【音频】-音乐(MP3)",
            "标准文件夹-【音频】-音乐(无损)",
            "标准文件夹-【音频】-音乐(处理中)",
            "标准文件夹-【视频】-动画片",
            "标准文件夹-【视频】-纪录片",
            "标准文件夹-【视频】-电影",
            "标准文件夹-【视频】-综艺节目",
            "标准文件夹-【视频】-red",
            "标准文件夹-【视频】-演唱会",
            "标准文件夹-【视频】-电视剧",
            "标准文件夹-【视频】-体育",
            "标准文件夹-【软件】-工具",
            "标准文件夹-【软件】-游戏",
            "标准文件夹-【图像】-照片",
            "标准文件夹-【图像】-图片",
            "标准文件夹-【文档】-工作文档",
            "标准文件夹-【文档】-书本",
            "标准文件夹-【备份】-硬盘备份",
            "标准文件夹-【备份】-硬盘镜像",
            "标准文件夹-【教育】-英语",
            "标准文件夹-【教育】-教程"
    };

    public AnalyzeDiskUsed() {
        step1_readPath();
    }

    public void step1_readPath() {
        Date start = new Date();
        log.info("start");

        File path = new File(rootPathStr);
        File[] xmlFiles = path.listFiles();
        for (File xmlFile : xmlFiles) {
            if (xmlFile.getName().endsWith("(export).xml")) {
//                log.info(xmlFile.getName());
                step2_readUsedPath(xmlFile);
            }
        }

        Date end = new Date();
        log.warn("Start:" + start);
        log.warn("End:" + end);
    }

    public void step2_readUsedPath(File xmlfile){
        try {
            SAXReader sax = new SAXReader();//创建一个SAXReader对象
            Document document = sax.read(xmlfile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
            Element root = document.getRootElement();//获取根节点
//            log.info(root);
            String catalogName = root.attributeValue("name");
            for(String usedPathStr:usedPaths){
//                log.info(usedPathStr);

                Element beginEle = root.element("Folder");
                String[] pathsStr = usedPathStr.split("-");
                String pathName = pathsStr[pathsStr.length-1];
                for(String pathStr:pathsStr){
//                    log.info(pathStr);
                    List<Element> eles = beginEle.elements("Folder");
//                    log.info(eles.size());
                   for(Element ele:eles){
//                       log.info("ele.attribute(name)="+ele.attributeValue("name"));
                       if(ele.attributeValue("name").equals(pathStr)){
                           beginEle = ele;
                       }
                   }
                }
//                log.info(beginEle);
                System.out.println(catalogName+","+pathName+","+beginEle.attributeValue("sonFilesSize"));
            }
        } catch (DocumentException e) {

        }
    }

    public static void main(String[] args) {
        AnalyzeDiskUsed analyzeDiskUsed = new AnalyzeDiskUsed();
    }
}
