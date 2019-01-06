package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisableCloseButtonDemo extends JFrame {
    public DisableCloseButtonDemo() throws HeadlessException {
        initialize();
    }

    private void initialize() {
        //
        // JFrame.DO_NOTHING_ON_CLOSE tell JFrame instance to do nothing
        // when a window closing event occurs.
        //
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JButton button = new JButton("Close");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setSize(new Dimension(100, 100));
        this.getContentPane().add(button);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DisableCloseButtonDemo().setVisible(true);
            }
        });
    }
}
