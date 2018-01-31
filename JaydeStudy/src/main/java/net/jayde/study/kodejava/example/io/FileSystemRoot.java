package net.jayde.study.kodejava.example.io;

import java.io.File;

public class FileSystemRoot {
    public static void main(String[] args) {
        //
        // List the available filesystem roots.
        //
        File[] root = File.listRoots();

        //
        // Iterate the entire filesystem roots.
        //
        for (int i = 0; i < root.length; i++) {
            File file = root[i];
            System.out.println("Root: " + file.getAbsolutePath());
        }
    }
}
