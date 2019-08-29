package com.jayde.apps.appDisk.test;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.test
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-06 16:52
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-06 16:52
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class BigTreeTest extends JPanel {
    public BigTreeTest() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        for (int a = 1; a < 10; a++) {
            String aa = "a" + a;
            DefaultMutableTreeNode elea = new DefaultMutableTreeNode(aa);
            for (int b = 1; b < 10; b++) {
                String bb = "b" + b;
                DefaultMutableTreeNode eleb = new DefaultMutableTreeNode(bb);
//                for (int c = 1; c < 10; c++) {
//                    String cc = "c" + c;
//                    DefaultMutableTreeNode elec = new DefaultMutableTreeNode(cc);
//                    for (int d = 1; d < 10; d++) {
//                        String dd = "d" + d;
//                        DefaultMutableTreeNode eled = new DefaultMutableTreeNode(dd);
//                        for (int e = 1; e < 100; e++) {
//                            String ee = "e" + e;
//                            DefaultMutableTreeNode elee = new DefaultMutableTreeNode(ee);
//                            for (int f = 1; f < 100; f++) {
//                                String ff = "f" + f;
//                                DefaultMutableTreeNode elef = new DefaultMutableTreeNode(ff);
//                                eled.add(elef);
//                            }
//                            eled.add(elee);
//                        }
//                        elec.add(eled);
//                    }
//                    eleb.add(elec);
//                }
                elea.add(eleb);
                System.out.println(bb + "   is ok");
            }
            root.add(elea);
            System.out.println(aa + "   is ok");
        }

        JTree jTree = new JTree(root);
        add(jTree);

        JButton jButton = new JButton("ex");
        add(jButton, BorderLayout.SOUTH);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                TreePath treePath = jTree.getSelectionPath();
//                System.out.println(treePath);
//                TreeModel treeModel = jTree.getModel();
                System.out.println(jTree.getLastSelectedPathComponent());
                TreePath treePath = jTree.getSelectionPath();
                DefaultMutableTreeNode select = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
                System.out.println(select);
                DefaultMutableTreeNode newone = new DefaultMutableTreeNode("newone");
                select.add(newone);
                System.out.println(newone);
            }
        });
    }

    public static void main(String[] args) {

        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(800, 600);
        jf.getContentPane().add(new BigTreeTest());
        jf.setVisible(true);
    }
}
