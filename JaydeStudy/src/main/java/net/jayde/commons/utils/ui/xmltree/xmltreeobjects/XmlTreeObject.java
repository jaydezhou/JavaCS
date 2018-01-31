package net.jayde.commons.utils.ui.xmltree.xmltreeobjects;

import org.dom4j.Element;

/**
 * Created by jayde on 2016-8-29.
 * 构建树节点用
 */
public class XmlTreeObject {
    XmlTreeObjectCommonInfo info;

    public XmlTreeObjectCommonInfo getInfo() {
        return info;
    }

    public XmlTreeObject() {
    }

    public XmlTreeObject(Element xmlnode) {
        info = new XmlTreeObjectCommonInfo();
        if (xmlnode != null) {
            System.out.println(xmlnode);
            info.setNodeType(xmlnode.getName().trim());
            switch (xmlnode.getName()) {
                case "Library":
                    info.setClasstype(xmlnode.attribute("lclass").getText().trim());
                    break;
                case "Folder":
                    info.setClasstype(xmlnode.attribute("fclass").getText().trim());
                    break;
                case "Node":
                    info.setClasstype(xmlnode.attribute("nclass").getText().trim());
                    break;
            }

            info.setName(xmlnode.attribute("name").getText().trim());
            info.setId(xmlnode.attribute("id").getText().trim());
            info.setIcon(xmlnode.attribute("icon").getText().trim());
//            info.setCname(xmlnode.attribute("class").getText().trim());
        }
    }

    @Override
    public String toString() {
        return info.getName();
    }
}
