package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingFrameState extends JFrame {
    public SwingFrameState() throws HeadlessException {
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        final JButton minimize = new JButton("Minimize");
        final JButton maximize = new JButton("Maximize");
        final JButton normal = new JButton("Normal");

        add(normal);
        add(minimize);
        add(maximize);

        pack();
        setSize(200, 200);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == normal) {
                    setExtendedState(Frame.NORMAL);
                } else if (e.getSource() == minimize) {
                    setExtendedState(Frame.ICONIFIED);
                } else if (e.getSource() == maximize) {
                    setExtendedState(Frame.MAXIMIZED_BOTH);
                }
            }
        };

        minimize.addActionListener(listener);
        maximize.addActionListener(listener);
        normal.addActionListener(listener);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingFrameState().setVisible(true);
            }
        });
    }
}
