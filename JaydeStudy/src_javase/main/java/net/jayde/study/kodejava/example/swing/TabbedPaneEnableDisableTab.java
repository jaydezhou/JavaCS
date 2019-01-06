package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class TabbedPaneEnableDisableTab extends JPanel {
    public TabbedPaneEnableDisableTab() {
        initializeUI();
    }

    private void initializeUI() {
        JTabbedPane pane = new JTabbedPane();
        pane.addTab("Tabs A", new JPanel());
        pane.addTab("Tabs B", new JPanel());
        pane.addTab("Tabs C", new JPanel());
        pane.addTab("Tabs D", new JPanel());

        //
        // Disable the first tab
        //
        pane.setEnabledAt(0, false);

        //
        // Disable the last tab, the pane.getTabCount() return the
        // number of tabs in the JTabbedPane. Because the index
        // start at 0 we need to substract the count by 1.
        //
        pane.setEnabledAt(pane.getTabCount() - 1, false);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));
        this.add(pane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TabbedPaneEnableDisableTab();
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
                TabbedPaneEnableDisableTab.showFrame();
            }
        });
    }
}
