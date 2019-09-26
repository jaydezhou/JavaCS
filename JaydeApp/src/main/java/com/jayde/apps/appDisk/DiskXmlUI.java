package com.jayde.apps.appDisk;

import com.jayde.apps.appDisk.bodisk.BoFolder;
import com.jayde.apps.appDisk.bodisk.BoSetFolder;
import com.jayde.apps.appDisk.bodisk.UtilBoFile;
import com.jayde.apps.appDisk.bolibrary.UtilDiskXml;
import com.jayde.apps.appDisk.util.MusicTreeRender;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.util
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-09-03 14:48
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-09-03 14:48
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class DiskXmlUI extends JPanel {
    boolean onlyVisible = false;

    public static void main(String[] args) {
        DiskXmlUI diskXmlUI = new DiskXmlUI();
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800, 600);
        jFrame.setLocation(200, 200);
        jFrame.getContentPane().add(diskXmlUI);
        jFrame.setVisible(true);
    }

    JTree jTree = new JTree();
    JButton jbDiskPath = new JButton("磁盘选择");
    JTextField jtfDiskPath = new JTextField("磁盘名称");
    JButton jbXmlFile = new JButton("文件选择");
    JTextField jtfXmlFile = new JTextField("文件名称");
    JButton jbDisk = new JButton("创建磁盘");
    JButton jbTree = new JButton("生成树");
    JButton jbLibrary = new JButton("创建库");


    public DiskXmlUI() {
        setLayout(new BorderLayout());

        JSplitPane jSplitPane = new JSplitPane();
        jSplitPane.setOneTouchExpandable(true);//让分割线显示出箭头
        jSplitPane.setContinuousLayout(true);//操作箭头，重绘图形
        jSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);//设置分割线方向
        jSplitPane.setDividerSize(1);//设置分割线的宽度
        jSplitPane.setDividerLocation(300);
        add(jSplitPane, BorderLayout.CENTER);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.getViewport().setView(jTree);
        jSplitPane.setLeftComponent(jScrollPane);

        JPanel operatePanel = new JPanel();
        operatePanel.setLayout(new GridBagLayout());
        jSplitPane.setRightComponent(operatePanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        jTree.setCellRenderer(new MusicTreeRender());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.05;
        operatePanel.add(jbDiskPath, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 8;
        gbc.gridheight = 1;
        gbc.weightx = 0.9;
        gbc.weighty = 0.05;
        jtfDiskPath.setEditable(false);
        jtfDiskPath.setText("/Users/mac/Desktop/标准文件夹[TEST]");
        operatePanel.add(jtfDiskPath, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.05;
        operatePanel.add(jbXmlFile, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 8;
        gbc.gridheight = 1;
        gbc.weightx = 0.9;
        gbc.weighty = 0.05;
        jtfXmlFile.setText("/Users/mac/Desktop");
        jtfXmlFile.setEditable(false);
        operatePanel.add(jtfXmlFile, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 7));
        buttonPanel.add(jbDisk);
        buttonPanel.add(jbLibrary);
        buttonPanel.add(jbTree);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 10;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.05;
        operatePanel.add(buttonPanel, gbc);

        JButton jbTable = new JButton("详细信息");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 10;
        gbc.gridheight = 7;
        gbc.weightx = 1.0;
        gbc.weighty = 0.85;
        operatePanel.add(jbTable, gbc);

        initAction();


    }

    private void initAction() {
        jbDiskPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fd = new JFileChooser();
                fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fd.showOpenDialog(null);
                File f = fd.getSelectedFile();
                jtfDiskPath.setText(f.getAbsolutePath());
            }
        });

        jbXmlFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fd = new JFileChooser();
                fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fd.showOpenDialog(null);
                File f = fd.getSelectedFile();
                jtfXmlFile.setText(f.getAbsolutePath());
            }
        });

        jbDisk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                UtilDiskXml.createXml(jtfDiskPath.getText(), jtfXmlFile.getText(), onlyVisible);

                BoFolder rootFolder = UtilBoFile.createBoFolder(new File(jtfDiskPath.getText()));
                System.out.println(rootFolder);
                UtilBoFile.exportXml(rootFolder, jtfDiskPath.getText(), jtfXmlFile.getText());
            }
        });

        jbLibrary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                UtilDiskXml.scanFiles(jtfDiskPath.getText(), jtfXmlFile.getText(), onlyVisible);
//                Object obj = jTree.getSelectionPath().getLastPathComponent();
                TreeSelectionModel selectionModel = jTree.getSelectionModel();
                TreePath lastTreePath = selectionModel.getSelectionPath();
                System.out.println("getSelectionPath:" + lastTreePath.getLastPathComponent());
                Object obj = lastTreePath.getLastPathComponent();
                if (obj instanceof DefaultMutableTreeNode) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) obj;
                    Object value = ((DefaultMutableTreeNode) obj).getUserObject();
                    if (value instanceof BoFolder) {
                        BoFolder selectFolder = (BoFolder) value;
                        System.out.println("BoFolder:" + selectFolder);
                        if (BoSetFolder.isLibraryFolder(selectFolder)) {
                            if (UtilBoFile.createLibrary(selectFolder)) {
                                jTree.updateUI();
                            }
                        }
                    }
                }
            }
        });

        jbTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File fromPath = new File(jtfDiskPath.getText());
                String pathName = fromPath.getName();
                String xmlfilename = jtfXmlFile.getText() + "/" + pathName + ".xml";
                System.out.println(xmlfilename);
//                DefaultMutableTreeNode rootNode = UtilDiskXml.createTreeByXml(xmlfilename);
                TreeModel defaultTreeModel = UtilBoFile.createTreeByXml(new File(xmlfilename));
                jTree.setModel(defaultTreeModel);
                jTree.setCellRenderer(new MusicTreeRender());
            }
        });

    }

}
