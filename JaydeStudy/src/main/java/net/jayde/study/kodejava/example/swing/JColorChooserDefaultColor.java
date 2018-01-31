package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;

public class JColorChooserDefaultColor extends JFrame {
    public JColorChooserDefaultColor() throws HeadlessException {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("JColorChooser Demo");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //
        // Creates an instance of JColorChooser and set Color.BLUE
        // as the default selected color.
        //
        JColorChooser jcc = new JColorChooser(Color.BLUE);

        getContentPane().add(jcc, BorderLayout.CENTER);
        this.pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JColorChooserDefaultColor().setVisible(true);
            }
        });
    }
}
