package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class TabbedPaneToolTips extends JPanel {
    public TabbedPaneToolTips() {
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));

        JTabbedPane pane = new JTabbedPane();
        ImageIcon tab1Icon = new ImageIcon(
                this.getClass().getResource("/tab1.png"));
        ImageIcon tab2Icon = new ImageIcon(
                this.getClass().getResource("/tab2.png"));
        ImageIcon tab3Icon = new ImageIcon(
                this.getClass().getResource("/tab3.png"));

        JPanel content1 = new JPanel();
        JPanel content2 = new JPanel();
        JPanel content3 = new JPanel();

        //
        // Add tabs to the JTabbedPane. The last parameter in the
        // add tab method is the tool tips for the tabs.
        //
        pane.addTab("Pool", tab1Icon, content1,
                "Pool Game Board");
        pane.addTab("Basketball", tab2Icon, content2,
                "Basketball Game Board");
        pane.addTab("Football", tab3Icon, content3,
                "Football Game Board");

        this.add(pane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TabbedPaneToolTips();
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
                TabbedPaneToolTips.showFrame();
            }
        });
    }
}
