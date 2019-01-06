package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class TabbedPaneTabColor extends JPanel {
    public TabbedPaneTabColor() {
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

        //
        // Set all tabs foreground color to black.
        //
        pane.setForeground(Color.BLACK);

        //
        // Set different background color for all tabs in 
        // JTabbedPane. The color from the first to the last
        // tab will be red, green yellow and orange.
        //
        pane.setBackgroundAt(0, Color.RED);
        pane.setBackgroundAt(1, Color.GREEN);
        pane.setBackgroundAt(2, Color.YELLOW);
        pane.setBackgroundAt(3, Color.ORANGE);

        this.add(pane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TabbedPaneTabColor();
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
                TabbedPaneTabColor.showFrame();
            }
        });
    }
}
