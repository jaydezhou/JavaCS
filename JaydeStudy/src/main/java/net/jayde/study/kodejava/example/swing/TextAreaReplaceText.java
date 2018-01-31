package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class TextAreaReplaceText extends JPanel {
    public TextAreaReplaceText() {
        initializeUI();
    }

    private void initializeUI() {
        String text =
                "The quick white fox jumps over the sleepy dog.";

        JTextArea textArea = new JTextArea(text);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        textArea.replaceRange("brown", 10, 15);
        textArea.replaceRange("lazy", 35, 41);

        this.setPreferredSize(new Dimension(300, 100));
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TextAreaReplaceText();
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
                TextAreaReplaceText.showFrame();
            }
        });
    }
}
