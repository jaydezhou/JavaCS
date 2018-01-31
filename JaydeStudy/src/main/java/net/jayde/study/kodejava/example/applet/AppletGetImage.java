package net.jayde.study.kodejava.example.applet;

import java.applet.Applet;
import java.awt.*;

public class AppletGetImage extends Applet {
    private Image logo;

    @Override
    public void init() {
        //
        // Get an Image object that can be painted on the Applet
        // screen. We need to supply the URL of the document as
        // the base location of the image and the location of the
        // image relative the the base URL. 
        //
        logo = getImage(getDocumentBase(), "/images/logo.png");
    }

    @Override
    public void paint(Graphics g) {
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        g.drawImage(logo, 10, 10, this);
    }
}
