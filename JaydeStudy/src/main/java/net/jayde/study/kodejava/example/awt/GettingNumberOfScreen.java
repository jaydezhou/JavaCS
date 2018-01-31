package net.jayde.study.kodejava.example.awt;

import java.awt.*;

public class GettingNumberOfScreen {
    public static void main(String[] args) {
        try {
            //
            // Get local graphics environment
            //
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();

            //
            // Returns an array of all of the screen GraphicsDevice objects.
            //
            GraphicsDevice[] devices = env.getScreenDevices();

            int numberOfScreens = devices.length;
            System.out.println("Number of available screens = " + numberOfScreens);
        } catch (HeadlessException e) {
            //
            // We'll get here if no screen devices was found.
            //
            e.printStackTrace();
        }
    }
}
