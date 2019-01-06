package com.jayde.apps.appBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GwgzToMarkdown implements ActionListener {
    JPanel jPanel = new JPanel();
    JTextArea fromField = new JTextArea();
    JTextArea toField = new JTextArea();
    JButton jButton = new JButton("Trans");

    public GwgzToMarkdown() {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1024, 768);
        jFrame.getContentPane().add(jPanel);
        jButton.addActionListener(this);
        jPanel.setLayout(new GridLayout());
        jPanel.add(new JScrollPane(fromField));
        jPanel.add(jButton);
        jPanel.add(new JScrollPane(toField));

        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        GwgzToMarkdown gwgzToMarkdown = new GwgzToMarkdown();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("click");
        String fromtext = fromField.getText();
        String toText = "";
        String[] chapters = fromtext.split("\n\n");
//        toField.setText(toText);
        for (int i = 0; i < chapters.length; i++) {
            System.out.println(i + ":");
            System.out.println(chapters[i]);
            String[] lines = chapters[i].split("\n");
            if (lines.length == 3) {
                lines[0] = lines[0].substring(0, 2) + "**" + lines[0].substring(2) + "**" + "\n\n";
                lines[1] = lines[1].substring(0, 2) + "`" + lines[1].substring(2) + "`" + "\n\n";
                lines[2] = "> " + lines[2].substring(2) + "\n\n";
                toText += lines[0] + lines[1] + lines[2];
            }
        }
        toField.setText(toText);

    }
}
