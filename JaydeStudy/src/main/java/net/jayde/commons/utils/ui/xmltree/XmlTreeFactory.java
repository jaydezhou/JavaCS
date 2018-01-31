package net.jayde.commons.utils.ui.xmltree;

import net.jayde.commons.utils.ui.xmltree.xmltreeobjects.XmlTreeObjectFolder;
import net.jayde.commons.utils.ui.xmltree.xmltreeobjects.XmlTreeObjectLibrary;
import net.jayde.commons.utils.ui.xmltree.xmltreeobjects.XmlTreeObjectNode;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.List;

/**
 * Created by jayde on 2016-8-29.
 */
public class XmlTreeFactory {
    static Logger logger = LoggerFactory.getLogger(XmlTreeFactory.class);

    public static DefaultMutableTreeNode createXmlTree(String xmlFileName, DefaultMutableTreeNode rootNode) {
        logger.info("XmlTreeFactory.createXmlTree()");
        //DefaultMutableTreeNode libraryNode = new DefaultMutableTreeNode();
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File(xmlFileName));
            Element ele = document.getRootElement();
//            logger.info(ele.getText());
//            logger.info(ele.toString());
            List<Element> libraries = ele.elements("Library");
            logger.info("list's count=",libraries.size());
            for (int i = 0; i < libraries.size(); i++) {
//                logger.info(libraries.get(i).attribute("name").getText());
                Element eleLibrary = libraries.get(i);
                XmlTreeObjectLibrary library = new XmlTreeObjectLibrary(eleLibrary);
                DefaultMutableTreeNode libraryNode = new DefaultMutableTreeNode(library);
                cycleFoler(eleLibrary, libraryNode);
                rootNode.add(libraryNode);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        logger.info("rootnode's name=",rootNode.toString());
        logger.info("rootnode's son count=",rootNode.getChildCount());
        return rootNode;
    }

    public static void cycleFoler(Element eleLibrary, DefaultMutableTreeNode folderNode) {
        List<Element> eleNodes = eleLibrary.elements("Node");
        for (int i = 0; i < eleNodes.size(); i++) {
            Element eleNode = eleNodes.get(i);
            XmlTreeObjectNode node = new XmlTreeObjectNode(eleNode);
            DefaultMutableTreeNode sonNode = new DefaultMutableTreeNode(node);
            folderNode.add(sonNode);
        }
        List<Element> eleFolders = eleLibrary.elements("Folder");
        if (eleFolders.size() == 0) return;
        for (int i = 0; i < eleFolders.size(); i++) {
            //System.out.println(eleFolders.get(i).attribute("name").getText());
            Element eleFolder = eleFolders.get(i);
            XmlTreeObjectFolder folder = new XmlTreeObjectFolder(eleFolder);
            DefaultMutableTreeNode sonFolderNode = new DefaultMutableTreeNode(folder);

            if (eleFolder.elements("Folder").size() >= 0) {
                cycleFoler(eleFolder, sonFolderNode);
            }
            folderNode.add(sonFolderNode);
        }
    }

    public static void main(String[] args) {
        /*
        //为了判断当前路径
        File f = new File(".");
        String absolutePath = f.getAbsolutePath();
        System.out.println(absolutePath);
        */
        String filename = "/Users/jayde/IdeaProjects/jaydemini/Books.xml";
//        filename = "out/production/mini/xml/Librarys.xml";
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("root");
        XmlTreeFactory.createXmlTree(filename, rootNode);
        DefaultMutableTreeNode libraryNode = (DefaultMutableTreeNode)rootNode.getFirstChild();
        DefaultMutableTreeNode folderNode = (DefaultMutableTreeNode)libraryNode.getFirstChild();

        System.out.println(folderNode);
        System.out.println(folderNode.getFirstChild());
        System.out.println(folderNode.getChildCount());
//        JTree jtree = new JTree(rootNode);
//        JFrame jf = new JFrame();
//        JPanel jp = new JPanel();
//        jf.setSize(400, 300);
//        jp.add(jtree);
//        jf.getContentPane().add(jp);
//        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jf.setVisible(true);
    }
}
