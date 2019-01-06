package net.jayde.study.kodejava.example.awt.geom;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class DrawVerticalText extends JPanel {
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        //
        // Define rendering hint, font name, font style and font size
        //
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font(Font.SERIF, Font.BOLD, 20));

        //
        // Rotate 90 degree to make a vertical text
        //
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.toRadians(90), 10, 10);
        g2.setTransform(at);
        g2.drawString("This is a vertical text", 10, 10);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Draw Vertical Text Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new DrawVerticalText());
        frame.pack();
        frame.setSize(200, 300);
        frame.setVisible(true);
    }
}
