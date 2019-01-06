package net.jayde.study.kodejava.example.awt.geom;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class DrawRoundRectangle extends JApplet {
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(Color.GREEN);
        g2.setStroke(new BasicStroke(2.0f));

        double x = 50;
        double y = 50;
        double w = x + 250;
        double h = y + 100;
        g2.draw(new RoundRectangle2D.Double(x, y, w, h, 50, 50));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JApplet applet = new DrawRoundRectangle();
        frame.getContentPane().add(applet, BorderLayout.CENTER);

        frame.pack();
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
