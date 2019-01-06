package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowClosingExample extends JFrame {
    public static void main(String[] args) {
        WindowClosingExample frame = new WindowClosingExample();
        frame.setSize(new Dimension(200, 200));
        frame.add(new Button("Hello World"));

        //
        // Add window listener by implementing WindowAdapter class to the
        // frame instance. To handle the close event we just need to implement
        // the windowClosing() method.
        //
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //
        // Show the frame
        // 
        frame.setVisible(true);
    }
}
