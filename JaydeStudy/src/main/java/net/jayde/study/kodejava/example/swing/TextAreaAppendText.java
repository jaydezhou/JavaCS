package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class TextAreaAppendText extends JPanel {
    public TextAreaAppendText() {
        initializeUI();
    }

    private void initializeUI() {
        String text = "The quick brown fox ";

        JTextArea textArea = new JTextArea(text);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        String appendText = "jumps over the lazy dog.";
        textArea.append(appendText);

        this.setPreferredSize(new Dimension(400, 200));
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TextAreaAppendText();
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
                TextAreaAppendText.showFrame();
            }
        });
    }
}
