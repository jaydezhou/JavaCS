package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class JSliderDemo extends JPanel implements ChangeListener {
    private JTextField field;

    public JSliderDemo() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 100));

        //
        // Creates an instance of JSlider with a horizontal
        // orientation. Define 0 as the minimal value and
        // 50 as the maximum value. The initial value is set
        // to 10.
        //
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 10);

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(10);

        slider.addChangeListener(this);

        JLabel label = new JLabel("Selected Value:");
        field = new JTextField(5);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(label);
        panel.add(field);

        add(slider, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);
    }

    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();

        //
        // Get the selection value of JSlider
        //
        field.setText(String.valueOf(slider.getValue()));
    }

    public static void showFrame() {
        JPanel panel = new JSliderDemo();
        panel.setOpaque(true);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("JSlider Demo");
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JSliderDemo.showFrame();
            }
        });
    }
}
