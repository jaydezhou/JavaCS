package net.jayde.study.kodejava.example.swing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FrameIconExample extends JFrame {
    public static void main(String[] args) {
        FrameIconExample frame = new FrameIconExample();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //
        // Set the window size and its title
        //
        frame.setSize(new Dimension(300, 200));
        frame.setTitle("Frame Icon Example");

        //
        // Read the image that will be used as the application icon. 
        // Using "/" in front of the image file name will locate the 
        // image at the root folder of our application. If you don't 
        // use a "/" then the image file should be on the same folder 
        // with your class file.
        //
        BufferedImage image = null;
        try {
            image = ImageIO.read(
                    frame.getClass().getResource("/colors.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setIconImage(image);

        // Display the form
        frame.setVisible(true);
    }
}
