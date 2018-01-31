package net.jayde.study.kodejava.example.swing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TextPaneWithBackgroundImage extends JFrame {
    public TextPaneWithBackgroundImage() {
        initUI();
    }

    private void initUI() {
        setSize(200, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        String imageFile = "/tree.jpg";
        CustomTextPane textPane = new CustomTextPane(imageFile);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(getWidth(), getHeight()));
        getContentPane().add(scrollPane);
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TextPaneWithBackgroundImage().setVisible(true);
            }
        });
    }
}

/**
 * A custom text pane that have a background image. To customize the
 * JTextPane we override the paintComponent(Graphics g) method. We have
 * to draw an image before we call the super class paintComponent method.
 */
class CustomTextPane extends JTextPane {
    private String imageFile;

    /**
     * Constructor of custome text pane.
     *
     * @param imageFile image file name for the text pane background.
     */
    CustomTextPane(String imageFile) {
        super();
        //
        // To be able to draw the background image the component must
        // not be opaque.
        //
        setOpaque(false);
        setForeground(Color.BLUE);

        this.imageFile = imageFile;
    }

    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage image = null;
        try {
            //
            // Load an image for the background image of out JTextPane.
            //
            image = ImageIO.read(getClass().getResourceAsStream(imageFile));
            g.drawImage(image, 0, 0, (int) getSize().getWidth(),
                    (int) getSize().getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.paintComponent(g);
    }
}
