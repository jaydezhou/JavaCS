package com.jayde.codesamples.jdk.swing.tree.createTree;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by jayde on 2016-11-27 17:04.
 * 版权所有
 */
public class DemoOfCreateTree {
    public JTree CreateTreeByDefault(){
        JTree jTree = new JTree();
        return jTree;
    }
    public JTree CreateTreeByNode(){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        JTree jTree = new JTree(root);
        return jTree;
    }
    public JTree CreateTreeByModel(){
        JTree jTree = new JTree();
        return jTree;
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(1024,768);
        JPanel jp = new JPanel();

        /**/
        DemoOfCreateTree demo = new DemoOfCreateTree();
        JTree jTree = demo.CreateTreeByNode();
        jp.add(jTree);
        /**/

        jf.getContentPane().add(jp);
        jf.setVisible(true);
    }
}
