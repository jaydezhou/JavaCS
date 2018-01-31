package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Get the size of our screen
                Dimension screenSize =
                        Toolkit.getDefaultToolkit().getScreenSize();

                MainForm mainForm = new MainForm();
                mainForm.setDefaultCloseOperation(
                        JFrame.EXIT_ON_CLOSE);

                mainForm.setSize(250, 250);

                // Calculates the position where the MainForm
                // should be paced on the screen.
                mainForm.setLocation((screenSize.width -
                        mainForm.getWidth()) / 2,
                        (screenSize.height -
                                mainForm.getHeight()) / 2);

                mainForm.pack();
                mainForm.setVisible(true);
            }
        });
    }
}
