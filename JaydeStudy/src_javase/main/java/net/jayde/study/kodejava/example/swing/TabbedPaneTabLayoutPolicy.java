package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class TabbedPaneTabLayoutPolicy extends JPanel {
    public TabbedPaneTabLayoutPolicy() {
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));

        //
        // Creates a JTabbedPane with scroll tab layout policy
        //
        JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP,
                JTabbedPane.SCROLL_TAB_LAYOUT);
        pane.addTab("One", createPanel("One"));
        pane.addTab("Two", createPanel("Two"));
        pane.addTab("Three", createPanel("Three"));
        pane.addTab("Four", createPanel("Four"));
        pane.addTab("Five", createPanel("Five"));
        pane.addTab("Six", createPanel("Six"));
        pane.addTab("Seven", createPanel("Seven"));
        pane.addTab("Eight", createPanel("Eight"));
        pane.addTab("Nine", createPanel("Nine"));
        pane.addTab("Ten", createPanel("Ten"));

        this.add(pane, BorderLayout.CENTER);
    }

    public JPanel createPanel(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel(title), BorderLayout.NORTH);
        return panel;
    }

    public static void showFrame() {
        JPanel panel = new TabbedPaneTabLayoutPolicy();
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
                TabbedPaneTabLayoutPolicy.showFrame();
            }
        });
    }
}
