package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TextAreaSetTabSize extends JPanel {
    public TextAreaSetTabSize() {
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));

        JLabel label = new JLabel("Tab size: ");
        Object[] items = {2, 4, 8, 10, 20};
        final JComboBox comboBox = new JComboBox(items);

        JPanel tabPanel = new JPanel(new FlowLayout());
        tabPanel.add(label);
        tabPanel.add(comboBox);

        this.add(tabPanel, BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        //
        // Sets the number of characters to expand tabs to.
        //
        textArea.setTabSize((Integer) comboBox.getSelectedItem());
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                textArea.setTabSize((Integer) comboBox.getSelectedItem());
            }
        });

        JScrollPane pane = new JScrollPane(textArea);
        pane.setPreferredSize(new Dimension(400, 200));
        pane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(pane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TextAreaSetTabSize();
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
                TextAreaSetTabSize.showFrame();
            }
        });
    }
}
