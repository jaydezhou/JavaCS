package net.jayde.study.kodejava.example.awt;

import java.awt.*;

public class MouseNumberOfButtons {
    public static void main(String[] args) {
        //
        // Get the number of buttons on the mouse. On systems without a
        // mouse, returns -1. HeadlessException if
        // GraphicsEnvironment.isHeadless() returns true
        //
        int numberOfButtons = MouseInfo.getNumberOfButtons();
        System.out.println("Mouse Number Of Buttons = " + numberOfButtons);
    }
}
