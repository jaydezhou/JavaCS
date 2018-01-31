package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class TabbedPaneWithIcon extends JPanel {
    public TabbedPaneWithIcon() {
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

        pane.addTab("Pool", tab1Icon, content1);
        pane.addTab("Basketball", tab2Icon, content2);
        pane.addTab("Football", tab3Icon, content3);

        this.add(pane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TabbedPaneWithIcon();
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
                TabbedPaneWithIcon.showFrame();
            }
        });
    }
}
