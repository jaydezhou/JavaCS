package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class JColorChooserColorSelection extends JFrame {
    private JColorChooser jcc = null;
    private JLabel label = null;

    public JColorChooserColorSelection() {
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        jcc = new JColorChooser();
        jcc.getSelectionModel().addChangeListener(new ColorSelection());
        getContentPane().add(jcc, BorderLayout.PAGE_START);

        label = new JLabel("Selected Font Color", JLabel.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        label.setForeground(Color.BLACK);
        label.setPreferredSize(new Dimension(100, 100));
        getContentPane().add(label, BorderLayout.CENTER);
        this.pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JColorChooserColorSelection().setVisible(true);
            }
        });
    }

    /**
     * A ChangeListener implementation for listening the color
     * selection of the JColorChooser component.
     */
    class ColorSelection implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            Color color = jcc.getColor();
            label.setForeground(color);
        }
    }
}
