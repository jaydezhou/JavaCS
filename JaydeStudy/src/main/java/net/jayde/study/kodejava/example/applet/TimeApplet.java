package net.jayde.study.kodejava.example.applet;

import java.applet.Applet;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeApplet extends Applet implements Runnable {
    private DateFormat formatter = null;
    private Thread t = null;

    public void init() {
        formatter = new SimpleDateFormat("hh:mm:ss");
        t = new Thread(this);
    }

    public void start() {
        t.start();
    }

    public void stop() {
        t = null;
    }

    public void paint(Graphics g) {
        Date now = Calendar.getInstance().getTime();
        //
        // Show the current time on the browser status bar
        //
        this.showStatus(formatter.format(now));
    }

    public void run() {
        int delay = 1000;
        try {
            while (t == Thread.currentThread()) {
                //
                // Repaint the applet every on second
                //
                repaint();
                Thread.sleep(delay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
