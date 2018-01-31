package net.jayde.commons.utils.ui.xmltree;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by jayde on 2016-10-9 15:49.
 * 版权所有
 */
public class TreeNodeUtil {
    public static String NodeToText(DefaultMutableTreeNode node,int level){
        String text = "";
        for (int i = 0; i < level; i++) {
           text=text+"  ";
        }

        text = text+"DefaultMutableTreeNode info:[";
        text = text+node.getUserObject().toString()+"]\n";
        for (int i = 0; i < node.getChildCount(); i++) {
         DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
            text = text+ NodeToText(child,level+1);
        }
        return text;
    }

}
