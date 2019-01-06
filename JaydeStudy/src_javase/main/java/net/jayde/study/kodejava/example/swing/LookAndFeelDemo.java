package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LookAndFeelDemo extends JFrame {
    public LookAndFeelDemo() {
        initComponents();
    }

    public void initComponents() {
        setSize(200, 200);
        setTitle("LAF Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Look and Feel");

        final JFrame frame = this;

        //
        // Get all the available look and feel that we are going to use for 
        // creating the JMenuItem and assign the action listener to handle
        // the selection of menu item to change the look and feel.
        //
        UIManager.LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
        for (int i = 0; i < lookAndFeelInfos.length; i++) {
            final UIManager.LookAndFeelInfo lookAndFeelInfo = lookAndFeelInfos[i];
            JMenuItem item = new JMenuItem(lookAndFeelInfo.getName());
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        //
                        // Set the look and feel for the frame and update the UI 
                        // to use a new selected look and feel.
                        //
                        UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                        SwingUtilities.updateComponentTreeUI(frame);
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (InstantiationException e1) {
                        e1.printStackTrace();
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedLookAndFeelException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            menu.add(item);
        }

        menuBar.add(menu);

        getContentPane().add(menuBar);
        getContentPane().add(new JButton("Hello"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LookAndFeelDemo().setVisible(true);
            }
        });
    }
}
