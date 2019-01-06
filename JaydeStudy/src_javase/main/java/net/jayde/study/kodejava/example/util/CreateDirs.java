package org.kodejava.example.util;

import java.io.File;

public class CreateDirs {
    public static void main(String[] args) {
        //
        // Define a deep directory structures. Next we wanted to create all the
        // directories.
        //
        String directories = "D:\\a\\b\\c\\d\\e\\f\\g\\h\\i";
        File file = new File(directories);

        //
        // The mkdirs will create folder including any necessary but nonexistence
        // parent directories. This method returns true if and only if the directory
        // was created along with all necessary parent directories.
        //
        boolean result = file.mkdirs();
        System.out.println("Status = " + result);
    }
}
