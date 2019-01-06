package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class TabbedPaneSelection extends JPanel
        implements ChangeListener {
    public TabbedPaneSelection() {
        initializeUI();
    }

    private void initializeUI() {
        JTabbedPane pane = new JTabbedPane();
        pane.addChangeListener(this);

        JPanel dashboardPanel = new JPanel();
        pane.addTab("Dashboard", dashboardPanel);

        JPanel accountPanel = new JPanel();
        pane.addTab("Account", accountPanel);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));
        this.add(pane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TabbedPaneSelection();
        panel.setOpaque(true);

        JFrame frame = new JFrame("JTabbedPane Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void stateChanged(ChangeEvent e) {
        JTabbedPane pane = (JTabbedPane) e.getSource();
        int selectedIndex = pane.getSelectedIndex();
        System.out.println("selectedIndex = " + selectedIndex);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TabbedPaneSelection.showFrame();
            }
        });
    }
}
