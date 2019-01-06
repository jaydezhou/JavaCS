package net.jayde.study.kodejava.example.awt;

import java.awt.*;

public class FontFamilyNameList {
    public static void main(String[] args) {
        //
        // Get all available font family names from GraphicsEnvironment
        //
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String familyNames[] = ge.getAvailableFontFamilyNames();

        //
        // Iterates familyNames array to display the available font's family names
        //
        for (String familyName : familyNames) {
            System.out.println("Family names: " + familyName);
        }
    }
}
