package net.jayde.study.kodejava.example.awt.geom;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class DrawDashedStroke extends JApplet {
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        float[] dash = {10.0f, 5.0f, 3.0f};

        //
        // Creates a dashed stroke
        //
        Stroke dashed = new BasicStroke(2.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);

        g2.setStroke(dashed);
        g2.setPaint(Color.RED);
        g2.draw(new RoundRectangle2D.Double(50, 50, 300, 100, 10, 10));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Draw Dashed Stroke Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JApplet applet = new DrawDashedStroke();
        frame.getContentPane().add(applet);
        frame.pack();
        frame.setSize(new Dimension(400, 250));
        frame.setVisible(true);
    }
}
