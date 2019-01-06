package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class InvertedJSlider extends JPanel {
    public InvertedJSlider() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 100));

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);

        //
        // Reverse the value-range of a JSlider. On a normal
        // horizontal JSlider the maximum value is on the right
        // side. Specifying inverted to true makes the maximum
        // value placed on the left side.
        //
        slider.setInverted(true);

        add(slider, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new InvertedJSlider();

        JFrame frame = new JFrame("Inverted JSlider");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                InvertedJSlider.showFrame();
            }
        });
    }
}
