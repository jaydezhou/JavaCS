package net.jayde.study.kodejava.example.awt;

import java.awt.*;

public class MouseInfoDemo {
    public static void main(String[] args) {
        int i = 0;
        while (i < 10) {
            //
            // Get the location of our mouse x and y coordinate from
            // the PointerInfo location object.
            //
            Point location = MouseInfo.getPointerInfo().getLocation();
            double x = location.getX();
            double y = location.getY();

            System.out.println("x = " + x);
            System.out.println("y = " + y);

            try {
                Thread.sleep(1000);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
