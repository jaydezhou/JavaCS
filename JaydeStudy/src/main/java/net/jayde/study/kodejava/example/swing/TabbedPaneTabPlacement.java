package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class TabbedPaneTabPlacement extends JPanel {
    public TabbedPaneTabPlacement() {
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));

        //
        // Creates a JTabbedPane with tabs at the bottom.
        //
        JTabbedPane pane = new JTabbedPane(JTabbedPane.BOTTOM);
        pane.addTab("Tab 1", createPanel("Panel 1"));
        pane.addTab("Tab 1", createPanel("Panel 2"));
        pane.addTab("Tab 3", createPanel("Panel 3"));

        this.add(pane, BorderLayout.CENTER);
    }

    public JPanel createPanel(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel(title), BorderLayout.NORTH);
        return panel;
    }

    public static void showFrame() {
        JPanel panel = new TabbedPaneTabPlacement();
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
                TabbedPaneTabPlacement.showFrame();
            }
        });
    }
}
