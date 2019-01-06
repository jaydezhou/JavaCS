package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class TabbedPaneDemo extends JPanel {
    public TabbedPaneDemo() {
        initializeUI();
    }

    private void initializeUI() {
        JTabbedPane pane = new JTabbedPane();

        JPanel dashboardPanel = new JPanel();
        dashboardPanel.add(new JLabel("Dashboard"));
        // Add Dashboard Tab
        pane.addTab("Dashboard", dashboardPanel);

        JPanel transactionPanel = new JPanel();
        transactionPanel.add(new JLabel("Transactions"));
        // Add Transactions Tab
        pane.addTab("Transactions", transactionPanel);

        JPanel accountPanel = new JPanel();
        accountPanel.add(new JLabel("Account"));
        // Add Account Tab
        pane.addTab("Account", accountPanel);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));
        this.add(pane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TabbedPaneDemo();
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
                TabbedPaneDemo.showFrame();
            }
        });
    }
}
