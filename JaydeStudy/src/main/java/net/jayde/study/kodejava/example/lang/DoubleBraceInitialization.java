package net.jayde.study.kodejava.example.lang;

import java.util.ArrayList;
import java.util.List;

public class DoubleBraceInitialization {
    public static void main(String[] args) {
        //
        // Creates a list of colors and add three colors of
        // Red, Green and Blue.
        //
        List<String> colors1 = new ArrayList<String>();
        colors1.add("Red");
        colors1.add("Green");
        colors1.add("Blue");


        //
        // Creates another list of colors and add three colors
        // using the double brace initialization.
        //
        List<String> colors2 = new ArrayList<String>() {{
            add("Red");
            add("Green");
            add("Blue");
        }};

        for (String color : colors2) {
            System.out.println("Color = " + color);
        }
    }
}
