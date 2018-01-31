package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class TextAreaNotEditable extends JPanel {
    public TextAreaNotEditable() {
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));

        JTextArea textArea = new JTextArea(5, 40);
        textArea.setText("The quick brown fox jumps over the lazy dog.");

        //
        // By default the JTextAread is editable, calling
        // setEditable(false) produce uneditable JTextArea.
        //
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TextAreaNotEditable();
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
                TextAreaNotEditable.showFrame();
            }
        });
    }
}
