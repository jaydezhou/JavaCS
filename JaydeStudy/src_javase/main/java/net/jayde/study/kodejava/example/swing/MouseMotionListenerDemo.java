package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseMotionListenerDemo extends JFrame {
    public MouseMotionListenerDemo() {
        initComponents();
    }

    protected void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        JTextArea textArea = new JTextArea("Hello World... try to move the mouse, click and drag it...");
        textArea.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                System.out.println("Mouse Dragged...");
            }

            public void mouseMoved(MouseEvent e) {
                System.out.println("Mouse Moved...");
            }
        });

        getContentPane().add(textArea);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MouseMotionListenerDemo().setVisible(true);
            }
        });
    }
}
