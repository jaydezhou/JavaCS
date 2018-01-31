package net.jayde.study.kodejava.example.awt;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RunningDefaultAppEdit {
    public static void main(String[] args) {
        File file = new File("logo.png");
        try {
            //
            // Edit a file using the default program for the
            // file type. In the example we will launch a
            // default registered program to edit a PNG image.
            //
            Desktop desktop = Desktop.getDesktop();
            desktop.edit(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
