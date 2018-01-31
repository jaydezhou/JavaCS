package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UppercaseTextFieldDemo extends JFrame {
    public UppercaseTextFieldDemo() throws HeadlessException {
        initComponents();
    }

    protected void initComponents() {
        //
        // Set default form size, closing event and layout manager
        //
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));

        //
        // Create a label and text field for our demo application and add the
        // component to the frame content pane object.
        //
        JLabel usernameLabel = new JLabel("Username: ");
        JTextField usernameTextField = new JTextField();
        usernameTextField.setPreferredSize(new Dimension(100, 20));
        getContentPane().add(usernameLabel);
        getContentPane().add(usernameTextField);

        //
        // Register a KeyListener for the text field. Using the KeyAdapter class
        // allow us implement the only key listener event that we want to listen,
        // in this example we use the keyReleased event.
        //
        usernameTextField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                textField.setText(text.toUpperCase());
            }

            public void keyTyped(KeyEvent e) {
                // TODO: Do something for the keyTyped event
            }

            public void keyPressed(KeyEvent e) {
                // TODO: Do something for the keyPressed event
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //
                // Create a new instance of our application and display it.
                //
                new UppercaseTextFieldDemo().setVisible(true);
            }
        });
    }
}
