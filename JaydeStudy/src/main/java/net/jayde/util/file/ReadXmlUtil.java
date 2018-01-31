package net.jayde.util.file;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

public class ReadXmlUtil {
    public static void main(String[] args) {
        String fileName = "/Users/zhouyongjian/Desktop/163/album-10559.xml";
        Document doc = null;
        try {
            SAXReader reader = new SAXReader();
            doc = reader.read(new File(fileName));// 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            System.out.println(rootElt.getName());
            System.out.println(rootElt.attributeValue("data-id"));
            List<Element> listLi = rootElt.elements("li");
            for (Element eleLi : listLi) {
                System.out.println("----------------------------------");
                List<Element> listSonEles = eleLi.elements();
                for (Element ele : listSonEles) {
                    if (ele.getName().equals("div")) {
                        System.out.println(ele.attributeValue("title"));
                        Element eleImg = ele.element("img");
                        System.out.println(eleImg.attributeValue("src"));
                    } else {
//                        System.out.println(ele.getName());
                        if (ele.getName().equals("p") && ele.attributeCount() == 2) {
                            Element eleA = ele.element("a");
                            System.out.println(eleA.attributeValue("href"));
                        } else {
                            Element eleSpan = ele.element("span");
                            System.out.println(eleSpan.getStringValue());
                        }
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
    }
}
