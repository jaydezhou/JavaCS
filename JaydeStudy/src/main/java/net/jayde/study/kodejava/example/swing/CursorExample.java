package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class CursorExample extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CursorExample mainForm = new CursorExample();
                mainForm.setDefaultCloseOperation(
                        JFrame.EXIT_ON_CLOSE);
                mainForm.setSize(250, 250);

                // Here we create a hand shaped cursor!
                Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                mainForm.setCursor(cursor);

                mainForm.pack();
                mainForm.setVisible(true);
            }
        });
    }
}
