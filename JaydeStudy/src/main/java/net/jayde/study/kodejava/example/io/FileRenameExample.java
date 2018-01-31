package net.jayde.study.kodejava.example.io;

import java.io.File;
import java.io.IOException;

public class FileRenameExample {
    public static void main(String[] args) throws IOException {
        //
        // Creates a new file called OldHouses.csv
        //
        File oldFile = new File("OldHouses.csv");
        oldFile.createNewFile();

        //
        // Creates the target file.
        //
        File newFile = new File("NewHouses.csv");

        // 
        // The renameTo() method renames file or directory to a
        // new name by passing the new destination file.
        //
        oldFile.renameTo(newFile);
    }
}
