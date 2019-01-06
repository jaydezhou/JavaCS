package net.jayde.study.kodejava.example.awt;

import java.awt.*;

public class ScreenSizeExample {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        System.out.println("Screen Widht: " + screenSize.getWidth());
        System.out.println("Screen Height: " + screenSize.getHeight());
    }
}
