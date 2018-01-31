package net.jayde.study.kodejava.example.awt;

import java.awt.*;

public class FontNameList {
    public static void main(String[] args) {
        //
        // Get all available fonts from GraphicsEnvironment
        //
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = ge.getAllFonts();

        //
        // Iterates all available fonts and get their name and family name
        //
        for (Font font : fonts) {
            String fontName = font.getName();
            String familyName = font.getFamily();

            System.out.println("Font: " + fontName + "; family: " + familyName);
        }
    }
}
