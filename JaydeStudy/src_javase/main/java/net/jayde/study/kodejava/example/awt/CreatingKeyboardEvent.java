package net.jayde.study.kodejava.example.awt;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CreatingKeyboardEvent {
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();

            //
            // Create a three seconds delay.
            //
            robot.delay(3000);

            //
            // Genering key press event for writing the QWERTY letters
            //
            robot.keyPress(KeyEvent.VK_Q);
            robot.keyPress(KeyEvent.VK_W);
            robot.keyPress(KeyEvent.VK_E);
            robot.keyPress(KeyEvent.VK_R);
            robot.keyPress(KeyEvent.VK_T);
            robot.keyPress(KeyEvent.VK_Y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
