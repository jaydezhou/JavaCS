package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class JTreeNodeAutoExpandCollapse extends JFrame {
    public JTreeNodeAutoExpandCollapse() throws HeadlessException {
        initializeUI();
    }

    private void initializeUI() {
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode chapter1 = new DefaultMutableTreeNode("Chapter 1");
        DefaultMutableTreeNode sub1 = new DefaultMutableTreeNode("1.1");
        DefaultMutableTreeNode sub2 = new DefaultMutableTreeNode("1.2");
        DefaultMutableTreeNode sub3 = new DefaultMutableTreeNode("1.3");
        DefaultMutableTreeNode sub31 = new DefaultMutableTreeNode("1.3.1");
        DefaultMutableTreeNode sub32 = new DefaultMutableTreeNode("1.3.2");

        root.add(chapter1);
        chapter1.add(sub1);
        chapter1.add(sub2);
        chapter1.add(sub3);
        sub3.add(sub31);
        sub3.add(sub32);

        final JTree tree = new JTree(root);
        expandTree(tree, false);

        JScrollPane pane = new JScrollPane(tree);
        pane.setPreferredSize(new Dimension(200, 200));

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JButton expandAll = new JButton("Expand All");
        expandAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                expandTree(tree, true);
            }
        });

        JButton collapseAll = new JButton("Collapse All");
        collapseAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                expandTree(tree, false);
            }
        });

        buttonPanel.add(expandAll, BorderLayout.WEST);
        buttonPanel.add(collapseAll, BorderLayout.EAST);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void expandTree(JTree tree, boolean expand) {
        TreeNode root = (TreeNode) tree.getModel().getRoot();
        expandAll(tree, new TreePath(root), expand);
    }

    private void expandAll(JTree tree, TreePath path, boolean expand) {
        TreeNode node = (TreeNode) path.getLastPathComponent();

        if (node.getChildCount() >= 0) {
            Enumeration enumeration = node.children();
            while (enumeration.hasMoreElements()) {
                TreeNode n = (TreeNode) enumeration.nextElement();
                TreePath p = path.pathByAddingChild(n);

                expandAll(tree, p, expand);
            }
        }

        if (expand) {
            tree.expandPath(path);
        } else {
            tree.collapsePath(path);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JTreeNodeAutoExpandCollapse().setVisible(true);
            }
        });
    }
}
