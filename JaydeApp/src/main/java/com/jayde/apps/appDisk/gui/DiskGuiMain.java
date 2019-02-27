package com.jayde.apps.appDisk.gui;

import lombok.extern.log4j.Log4j;
import sun.tools.jconsole.Tab;

import javax.swing.*;
import java.awt.*;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.gui
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-02-25 17:35
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-02-25 17:35
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Log4j
public class DiskGuiMain extends JFrame {
    ButtonPanel buttonPanel = new ButtonPanel();
    TreePanel treePanel = new TreePanel();
    TablePanel tablePanel = new TablePanel();
    TipsPanel tipsPanel = new TipsPanel();

    public DiskGuiMain() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());

        } catch (final ClassNotFoundException | UnsupportedLookAndFeelException
                | IllegalAccessException | InstantiationException e) {
            log.error("unable to set look and feel: ", e);
        }
        getContentPane().add(initGUI());
        setVisible(true);
    }

    private JPanel initGUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(tipsPanel, BorderLayout.SOUTH);

        JSplitPane centerPanel = new JSplitPane();
        centerPanel.setLeftComponent(treePanel);
        centerPanel.setRightComponent(tablePanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

//        mainPanel.add(buttonPanel,BorderLayout.NORTH);
//        mainPanel.add(buttonPanel,BorderLayout.NORTH);
        return mainPanel;
    }

    public static void main(String[] args) {
        DiskGuiMain diskGuiMain = new DiskGuiMain();
    }
}

class ButtonPanel extends JPanel {
    public ButtonPanel() {
        setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(new JButton("Add"));
        buttonsPanel.add(new JButton("Folder"));
        buttonsPanel.add(new JButton("File"));
        buttonsPanel.add(new JButton("Hash"));
        buttonsPanel.add(new JButton("Update"));
        add(buttonsPanel,BorderLayout.CENTER);

        add(new JLabel("..."),BorderLayout.SOUTH);
    }
}

class TreePanel extends JScrollPane {
    public TreePanel() {
        getViewport().add(new JTree());
    }
}

class TablePanel extends JScrollPane {
    public TablePanel() {
        getViewport().add(new JTable());
    }
}

class TipsPanel extends JPanel {
    public TipsPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Tips...."));
    }
}