package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TabbedPaneKeyboardShortcut extends JPanel {
    public TabbedPaneKeyboardShortcut() {
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));

        JTabbedPane pane = new JTabbedPane();
        pane.addTab("A Tab", new JPanel());
        pane.addTab("B Tab", new JPanel());
        pane.addTab("C Tab", new JPanel());
        pane.addTab("D Tab", new JPanel());

        pane.setMnemonicAt(0, KeyEvent.VK_A);
        pane.setMnemonicAt(1, KeyEvent.VK_B);
        pane.setMnemonicAt(2, KeyEvent.VK_C);
        pane.setMnemonicAt(3, KeyEvent.VK_D);

        this.add(pane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TabbedPaneKeyboardShortcut();
        panel.setOpaque(true);

        JFrame frame = new JFrame("JTabbedPane Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TabbedPaneKeyboardShortcut.showFrame();
            }
        });
    }
}
