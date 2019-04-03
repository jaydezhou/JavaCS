package com.jayde.apps.appredmine;

import lombok.extern.log4j.Log4j;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appredmine
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-03-14 19:20
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-03-14 19:20
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Log4j
public class AnalyzePmbokText extends JPanel {
    JTextArea oldTextArea = new JTextArea("");
    JTextArea newTextArea = new JTextArea("");

    public AnalyzePmbokText() {
        oldTextArea.setBorder(BorderFactory.createEtchedBorder());
        oldTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    anyze();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    anyze();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    anyze();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        newTextArea.setBorder(BorderFactory.createRaisedBevelBorder());
        setLayout(new GridLayout());
        add(oldTextArea);
        add(newTextArea);
    }

    public void anyze() throws IOException {
//        log.info(oldTextArea.getText());
        String oldText = oldTextArea.getText();
        StringReader stringReader = new StringReader(oldText);
        BufferedReader br = new BufferedReader(stringReader);
        StringBuilder sb = new StringBuilder("");
        String line = null;
        String preline = null;

        while ((line = br.readLine()) != null) {
            if (sb.toString().length() > 0) {
                sb.append("\n");
            }

            if (line.endsWith(" ")) {
                line = line.substring(0, line.length() - 1);
            }

            if (line.startsWith("u   ")) {
                line = line.replaceAll("u   ", ">* ");
                if (preline != null && preline.startsWith(">* ") == false) {
                    sb.append("\n");
                }
            } else {
                if (line.contains(" ")) {
                    line = line.replaceAll(" ", "");
                }
                if (line.startsWith(">* ")) {
                    sb.append("\n");
                }
            }

            sb.append(line);
            preline = new String(line.getBytes());

        }
        newTextArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        AnalyzePmbokText analyzePmbokText = new AnalyzePmbokText();
        JFrame jFrame = new JFrame();
        jFrame.setSize(1024, 768);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().add(analyzePmbokText);
        jFrame.setVisible(true);
    }
}
