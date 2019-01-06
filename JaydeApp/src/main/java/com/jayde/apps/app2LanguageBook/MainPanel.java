package com.jayde.apps.app2LanguageBook;

import lombok.extern.log4j.Log4j;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.app2LanguageBook
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2018/11/24 下午3:43
 * @UpdateUser: The Modified user
 * @UpdateDate: 2018/11/24 下午3:43
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Log4j
public class MainPanel extends JPanel {
    JTextArea leftTextArea = new JTextArea("left");
    JTextArea rightTextArea = new JTextArea("right");
    JTextArea outTextArea = new JTextArea("out");
    JButton button = new JButton("OK");

    public MainPanel() {
        JPanel txtPanel = new JPanel();
        txtPanel.setLayout(new GridLayout());
        leftTextArea.setBorder(new LineBorder(Color.BLACK));
        rightTextArea.setBorder(new LineBorder(Color.BLACK));
        outTextArea.setBorder(new LineBorder(Color.BLACK));
        txtPanel.add(leftTextArea);
        txtPanel.add(outTextArea);
        txtPanel.add(rightTextArea);
        setLayout(new BorderLayout());
        add(txtPanel, BorderLayout.CENTER);
        add(button, BorderLayout.NORTH);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lefttext = leftTextArea.getText();
                String righttext = rightTextArea.getText();
                String[] lefts = lefttext.split("\n");
                String[] rights = righttext.split("\n");
                if(lefts.length!=rights.length){
                    log.error("行数不对");
                    return;
                }
                for(int i=0;i<lefts.length;i++){
                    log.info(lefts[i]);
                    log.warn(rights[i]);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(800, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().add(new MainPanel());
        jf.setVisible(true);
    }
}
