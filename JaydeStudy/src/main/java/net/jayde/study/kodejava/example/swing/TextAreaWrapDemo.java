package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class TextAreaWrapDemo extends JPanel {
    public TextAreaWrapDemo() {
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));

        JTextArea textArea = new JTextArea(5, 40);

        String text = "The quick brown fox jumps over the lazy dog. " +
                "The quick brown fox jumps over the lazy dog. " +
                "The quick brown fox jumps over the lazy dog.";
        textArea.setText(text);
        textArea.setFont(new Font("Arial", Font.BOLD, 18));

        //
        // Wrap the lines of the JTextArea if it does not fit in the
        // current allocated with of the JTextArea. 
        //
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TextAreaWrapDemo();
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
                TextAreaWrapDemo.showFrame();
            }
        });
    }
}
