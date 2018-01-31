package net.jayde.study.kodejava.example.io;

import java.io.File;

public class CreateDirectoryExample {
    public static void main(String[] args) {
        // Create an abstract directory
        File file = new File("kodejava");

        // Create the directory by calling mkdir() method. This
        // method return true if and only if the directory was
        // created. If false is returnned no directory is created
        // because for instance the directory was already exists.
        if (file.mkdir()) {
            System.out.println("Directory = " + file.getAbsolutePath() + ".");
        } else {
            System.out.println("No directory was created.");
        }
    }
}
