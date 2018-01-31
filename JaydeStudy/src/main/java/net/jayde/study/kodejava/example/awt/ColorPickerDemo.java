package net.jayde.study.kodejava.example.awt;

import java.awt.*;

public class ColorPickerDemo {
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();

            //
            // The the pixel color information at 20, 20
            //
            Color color = robot.getPixelColor(20, 20);

            //
            // Print the RGB information of the pixel color
            //
            System.out.println("Red   = " + color.getRed());
            System.out.println("Green = " + color.getGreen());
            System.out.println("Blue  = " + color.getBlue());

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
