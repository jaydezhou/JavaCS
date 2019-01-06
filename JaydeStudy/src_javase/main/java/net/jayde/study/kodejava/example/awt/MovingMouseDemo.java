package net.jayde.study.kodejava.example.awt;

import java.awt.*;
import java.awt.event.InputEvent;

public class MovingMouseDemo {
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();

            //
            // Move mouse cursor to 200, 200
            //
            robot.mouseMove(200, 200);

            //
            // Press the mouse button #1.
            //
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);

            //
            // Scroll the screen up for a mouse with a wheel support.
            //
            robot.mouseWheel(-100);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
