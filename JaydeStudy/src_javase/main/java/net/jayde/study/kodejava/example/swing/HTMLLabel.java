package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class HTMLLabel extends JFrame {
    public HTMLLabel() {
        setTitle("JLabel with HTML");
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        //
        // Create a JLabel object that display a string formatted using HTML.
        // 14 font size with red and italic.
        //
        JLabel label = new JLabel("<html><font size=\"14\" color=\"ff0000\"><i>Hello World</i></font></html>");
        container.add(label);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HTMLLabel().setVisible(true);
            }
        });
    }
}
