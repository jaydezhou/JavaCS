package net.jayde.study.kodejava.example.awt;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RunningDefaultAppOpen {
    public static void main(String[] args) {
        //
        // A reference to a text file
        //
        File file = new File("data.txt");

        try {
            Desktop desktop = Desktop.getDesktop();

            //
            // Open a file using the default program for the
            // file type. In the example we will launch a
            // default registered program to open a text file.
            // For example on Windows operating system this call
            // might launch a notepad.exe to open the file.
            //
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
