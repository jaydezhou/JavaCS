package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class TabbedPaneDisableIcon extends JPanel {
    private ImageIcon[] disableIcons = {
            new ImageIcon(this.getClass().getResource("/dis1.png")),
            new ImageIcon(this.getClass().getResource("/dis2.png")),
            new ImageIcon(this.getClass().getResource("/dis3.png")),
    };

    public TabbedPaneDisableIcon() {
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 100));

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

        for (int i = 0; i < pane.getTabCount(); i++) {
            pane.setDisabledIconAt(i, disableIcons[i]);
        }

        //
        // Disable the last tab to see the disabled icon displayed.
        //
        pane.setEnabledAt(pane.getTabCount() - 1, false);

        this.add(pane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TabbedPaneDisableIcon();
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
                TabbedPaneDisableIcon.showFrame();
            }
        });
    }
}
