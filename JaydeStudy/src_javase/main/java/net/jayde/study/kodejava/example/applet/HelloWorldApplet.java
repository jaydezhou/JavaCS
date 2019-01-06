package net.jayde.study.kodejava.example.applet;

import java.applet.Applet;
import java.awt.*;

public class HelloWorldApplet extends Applet {
    public void init() {
    }

    public void start() {
    }

    public void stop() {
    }

    public void destroy() {
    }

    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawString("Hello World", 50, 100);
    }
}
