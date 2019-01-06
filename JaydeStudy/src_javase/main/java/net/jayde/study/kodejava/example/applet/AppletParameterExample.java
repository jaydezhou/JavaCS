package net.jayde.study.kodejava.example.applet;

import java.applet.Applet;
import java.awt.*;

public class AppletParameterExample extends Applet {
    private String name = "";

    public void init() {
        //
        // Here we read a parameter named name from the applet tag definition
        // in our html file.
        //
        name = getParameter("name");
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawString("Hello " + name + ", Welcome to the Applet World.", 0, 0);
    }
}
