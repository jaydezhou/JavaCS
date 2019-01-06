package net.jayde.study.kodejava.example.io;

import java.io.File;

public class WritableExample {
    public static void main(String[] args) throws Exception {
        File file = new File("Writable.txt");

        // Create a file only if it doesn't exist.
        file.createNewFile();

        // Set file attribute to read only so that it cannot be written
        file.setReadOnly();

        // We are using the canWrite() method to check whether we can
        // modified file content.
        if (file.canWrite()) {
            System.out.println("File is writable!");
        } else {
            System.out.println("File is in read only mode!");
        }

        // Now make our file writable
        file.setWritable(true);

        // re-check the read-write status of file 
        if (file.canWrite()) {
            System.out.println("File is writable!");
        } else {
            System.out.println("File is in read only mode!");
        }
    }
}
