package net.jayde.study.kodejava.example.io;

import java.io.File;

public class FileDeleteExample {
    public static void main(String[] args) {
        // When want to delete a file named readme.txt
        File file = new File("readme.txt");

        // Checks if the file is exists before deletion.
        if (file.exists()) {
            System.out.println("Deleting file " + file.getAbsolutePath());
            // Use the delete method to delete the given file.
            file.delete();
        } else {
            System.out.println(file.getAbsolutePath() + " doesn't exists.");
        }
    }
}
