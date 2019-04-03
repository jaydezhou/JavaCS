package com.jayde.apps.appredmine;


import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.time.LocalDate;
import java.util.Date;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appredmine
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-03-20 22:34
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-03-20 22:34
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class CszxRedmine extends JPanel {
    CszxRedmineUtil cszxRedmineUtil = new CszxRedmineUtil();

    JPanel checkPanel;
    JPanel backupPanel;
    JPanel recordPanel;
    JPanel importPanel;

    public CszxRedmine() {
        JTabbedPane jTabbedPane = new JTabbedPane();
        initAllPanel();
        jTabbedPane.addTab("工作检查", checkPanel);
        jTabbedPane.addTab("备份数据", backupPanel);
        jTabbedPane.addTab("工作日志", recordPanel);
        jTabbedPane.addTab("导入记录", importPanel);
        setLayout(new BorderLayout());
        add(jTabbedPane, BorderLayout.CENTER);

    }

    private void initAllPanel() {
        initCheckPanel();
        initBackupPanel();
        initRecordPanel();
        initImportPanel();
    }

    private void initCheckPanel() {
        checkPanel = new JPanel();
        checkPanel.setLayout(new BorderLayout());
        JButton checkButton = new JButton("检查");
        JTextArea checkText = new JTextArea();
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner daySpinner = new JSpinner(model);
        daySpinner.setValue(new Date());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(daySpinner, "yyyy年MM月dd日");

        JFormattedTextField textField = ((JSpinner.DateEditor) daySpinner.getEditor()).getTextField();
        textField.setEditable(true);
        DefaultFormatterFactory factory = (DefaultFormatterFactory) textField.getFormatterFactory();
        DateFormatter formatter = (DateFormatter) factory.getDefaultFormatter();
        formatter.setAllowsInvalid(false);
        daySpinner.setEditor(editor);

        checkPanel.add(daySpinner, BorderLayout.NORTH);
        checkPanel.add(checkButton, BorderLayout.SOUTH);
        checkPanel.add(checkText, BorderLayout.CENTER);

        checkButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("checkButton:click");
                Date selectDay = (Date) daySpinner.getModel().getValue();
                String checkResultText = cszxRedmineUtil.checkAction(selectDay);
                System.out.println(checkResultText);
                checkText.setText(checkResultText);
            }
        });
    }

    private void initBackupPanel() {
        backupPanel = new JPanel();
        backupPanel.setLayout(new BorderLayout());

        JPanel backupPathPanel = new JPanel();
        backupPathPanel.setLayout(new BorderLayout());
        JTextField outpathField = new JTextField();
        JButton outpathButton = new JButton("选择路径");
        outpathField.setEditable(false);
        backupPathPanel.add(outpathField, BorderLayout.CENTER);
        backupPathPanel.add(outpathButton, BorderLayout.EAST);
        outpathButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFileChooser jfc = new JFileChooser();// 文件选择器
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int state = jfc.showOpenDialog(null);
                if (state == 1) {
                    return;
                } else {
                    File f = jfc.getSelectedFile();// f为选择到的目录
                    outpathField.setText(f.getAbsolutePath());
                }

            }
        });

        JButton backupButton = new JButton("备份");

        backupPanel.add(backupPathPanel, BorderLayout.NORTH);
        backupPanel.add(backupButton, BorderLayout.SOUTH);
    }

    private void initRecordPanel() {
        recordPanel = new JPanel();
        recordPanel.setLayout(new BorderLayout());

        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner monthSpinner = new JSpinner(model);
        monthSpinner.setValue(new Date());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(monthSpinner, "yyyy年MM月");
        monthSpinner.setEditor(editor);
        JButton recordButton = new JButton("生成");

        recordPanel.add(monthSpinner, BorderLayout.NORTH);
        recordPanel.add(recordButton, BorderLayout.SOUTH);

        recordButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("recordButton:click");
                Date now = (Date) monthSpinner.getValue();
                cszxRedmineUtil.recordAction(now);

            }
        });

    }

    private void initImportPanel() {
        importPanel = new JPanel();
        importPanel.setLayout(new BorderLayout());

        JPanel importPathPanel = new JPanel();
        importPathPanel.setLayout(new BorderLayout());
        JTextField importpathField = new JTextField();
        JButton importpathButton = new JButton("选择文件");
        importpathField.setEditable(false);
        importPathPanel.add(importpathField, BorderLayout.CENTER);
        importPathPanel.add(importpathButton, BorderLayout.EAST);
        importpathButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFileChooser jfc = new JFileChooser();// 文件选择器
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                XmlFileFilter xmlFileFilter = new XmlFileFilter();
                jfc.setFileFilter(xmlFileFilter);
                int state = jfc.showOpenDialog(null);
                if (state == 1) {
                    return;
                } else {
                    File f = jfc.getSelectedFile();// f为选择到的目录
                    importpathField.setText(f.getAbsolutePath());
                }

            }
        });

        JButton importButton = new JButton("导入");

        importPanel.add(importPathPanel, BorderLayout.NORTH);
        importPanel.add(importButton, BorderLayout.SOUTH);
    }


    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(1024, 768);
        jFrame.getContentPane().add(new CszxRedmine());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

    }
}

class XmlFileFilter extends FileFilter {
    public String getDescription() {
        return "*.xml";
    }

    public boolean accept(File file) {
        String name = file.getName();
        return file.isDirectory() || name.toLowerCase().endsWith(".xml");  // 仅显示目录和xls、xlsx文件
    }
}
