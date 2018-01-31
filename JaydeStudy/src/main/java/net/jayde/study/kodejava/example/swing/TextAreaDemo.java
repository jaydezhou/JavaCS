package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class TextAreaDemo extends JPanel {
    public TextAreaDemo() {
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));

        //
        // Creates a JTextArea with 5 rows and 40 colums. Then
        // wrap the JTextArea inside a JScrollPane for adding
        // scrolling abilities.
        //
        JTextArea textArea = new JTextArea(5, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TextAreaDemo();
        panel.setOpaque(true);

        JFrame frame = new JFrame("JTextArea Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TextAreaDemo.showFrame();
            }
        });
    }
}
