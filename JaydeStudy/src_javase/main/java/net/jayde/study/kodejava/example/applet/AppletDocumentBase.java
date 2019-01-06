package net.jayde.study.kodejava.example.applet;

import java.applet.Applet;
import java.awt.*;

public class AppletDocumentBase extends Applet {
    private Image logo;

    @Override
    public void init() {
        //
        // Locates logo image base on the URL of the document
        // where the Applet is embedded which is returned by
        // the getDocumentBase() method call.
        //
        // eg. http://localhost:8080/images/logo.jpg
        //
        logo = getImage(getDocumentBase(), "/images/logo.png");
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        //
        // Draw the logo image on the Applet surface.
        //
        g.drawImage(logo, 10, 10, this);
    }
}
