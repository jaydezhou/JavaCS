package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class TextAreaInsertText extends JPanel {
    public TextAreaInsertText() {
        initializeUI();
    }

    private void initializeUI() {
        String text = "The quick fox jumps the lazy dog.";

        JTextArea textArea = new JTextArea(text);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        textArea.insert("brown ", 10);
        textArea.insert("over ", 26);

        this.setPreferredSize(new Dimension(300, 100));
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TextAreaInsertText();
        panel.setOpaque(true);

        JFrame frame = new JFrame("JTextArea Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TextAreaInsertText.showFrame();
            }
        });
    }
}
