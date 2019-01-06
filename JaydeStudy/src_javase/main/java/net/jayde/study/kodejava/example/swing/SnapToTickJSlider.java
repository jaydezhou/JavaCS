package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class SnapToTickJSlider extends JPanel {
    public SnapToTickJSlider() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 100));

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 30, 0);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(10);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);

        //
        // Snaps to the closest tick mark next to where the user
        // positioned the knob.
        //
        slider.setSnapToTicks(true);

        add(slider, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new SnapToTickJSlider();

        JFrame frame = new JFrame("JSlider - Snap to Tick");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SnapToTickJSlider.showFrame();
            }
        });
    }
}
