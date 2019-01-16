package com.jayde.apps.appKnowledgeLibrary.ui;

import com.jayde.apps.appKnowledgeLibrary.bo.RedmineIssue;
import com.jayde.apps.appKnowledgeLibrary.bo.RedmineProject;
import com.jayde.apps.appKnowledgeLibrary.util.ReadFromRedmineDb;

import java.util.List;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appKnowledgeLibrary.ui
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-01-16 16:38
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-01-16 16:38
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class MainUI extends JFrame {
    JPanel mainPanel = new JPanel();
    JTree jTree;

    public MainUI() {
        initPanel();

        getContentPane().add(mainPanel);

        setTitle("Jredmine");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void initPanel() {
        JSplitPane jSplitPane = new JSplitPane();
//        jSplitPane.setDividerLocation(0.4);
//        jSplitPane.setSize(1024,768);

        JScrollPane leftScrollPane = new JScrollPane();
        initTree();
        leftScrollPane.getViewport().add(jTree);

        JScrollPane rightScrollPane = new JScrollPane();

        jSplitPane.setLeftComponent(leftScrollPane);
        jSplitPane.setRightComponent(rightScrollPane);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(jSplitPane, BorderLayout.CENTER);
    }

    public void initTree() {
        ReadFromRedmineDb readFromRedmineDb = new ReadFromRedmineDb();
        List<RedmineProject> listProjects = readFromRedmineDb.getProjectsAndIssues();
//        List<RedmineIssue> listIssues = readFromRedmineDb.getIssues();


        DefaultMutableTreeNode treeRootNode = new DefaultMutableTreeNode("项目集");
        DefaultTreeModel treeModel = new DefaultTreeModel(treeRootNode);
        for (RedmineProject redmineProject : listProjects) {
            DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode(redmineProject);
            treeRootNode.add(projectNode);
            for (RedmineIssue rootIssue : redmineProject.getListRootIssues()) {
                DefaultMutableTreeNode rootIssueNode = new DefaultMutableTreeNode(rootIssue);
                projectNode.add(rootIssueNode);
                initSonTree(rootIssueNode, rootIssue);
            }
        }
        jTree = new JTree(treeModel);
        RedmineTreeCellRenderer treeCellRenderer = new RedmineTreeCellRenderer();
        jTree.setCellRenderer(treeCellRenderer);

        jTree.setShowsRootHandles(true);
        jTree.setRootVisible(false);
    }

    public void initSonTree(DefaultMutableTreeNode parentNode, RedmineIssue parentIssue) {
        for (RedmineIssue sonIssue : parentIssue.getListSonIssues()) {
            DefaultMutableTreeNode sonIssueNode = new DefaultMutableTreeNode(sonIssue);
            parentNode.add(sonIssueNode);
            initSonTree(sonIssueNode, sonIssue);
        }
    }

    public static void main(String[] args) {
        MainUI mainUI = new MainUI();
    }
}

class RedmineTreeCellRenderer extends DefaultTreeCellRenderer {
    private static final long serialVersionUID = 1L;

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel, boolean expanded, boolean leaf, int row,
                                                  boolean hasFocus) {

        //执行父类原型操作
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
                row, hasFocus);

        setText(value.toString());

        if (sel) {
            setForeground(getTextSelectionColor());
        } else {
            setForeground(getTextNonSelectionColor());
        }

        //得到每个节点的TreeNode
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Object nodeValue = node.getUserObject();

        if (nodeValue instanceof RedmineProject) {
        }
        if (nodeValue instanceof RedmineIssue) {
            RedmineIssue redmineIssue = (RedmineIssue) nodeValue;
            switch (redmineIssue.getTrackerType()) {
                case "4":
                    this.setIcon(new ImageIcon("/Users/mac/IdeaProjects/JavaCS/JaydeApp/src/main/java/com/jayde/apps/appKnowledgeLibrary/ui/tracker_4.png"));
                    break;
                case "5":
                    this.setIcon(new ImageIcon("/Users/mac/IdeaProjects/JavaCS/JaydeApp/src/main/java/com/jayde/apps/appKnowledgeLibrary/ui/tracker_5.png"));
                    break;
                case "6":
                    this.setIcon(new ImageIcon("/Users/mac/IdeaProjects/JavaCS/JaydeApp/src/main/java/com/jayde/apps/appKnowledgeLibrary/ui/tracker_6.png"));
                    break;
                case "7":
                    this.setIcon(new ImageIcon("/Users/mac/IdeaProjects/JavaCS/JaydeApp/src/main/java/com/jayde/apps/appKnowledgeLibrary/ui/tracker_7.png"));
                    break;


            }
        }

        return this;
    }

}