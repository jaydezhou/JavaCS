package net.jayde.study.kodejava.example.awt;

import java.awt.*;

public class GettingScreenDisplayModeInformation {
    public static void main(String[] args) {
        //
        // Get local graphics environment
        //
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();

        GraphicsDevice[] devices = env.getScreenDevices();

        int sequence = 1;
        for (GraphicsDevice device : devices) {
            System.out.println("Screen Number [" + (sequence++) + "]");
            System.out.println("Width       : " + device.getDisplayMode().getWidth());
            System.out.println("Height      : " + device.getDisplayMode().getHeight());
            System.out.println("Refresh Rate: " + device.getDisplayMode().getRefreshRate());
            System.out.println("Bit Depth   : " + device.getDisplayMode().getBitDepth());
            System.out.println("");
        }
    }
}
