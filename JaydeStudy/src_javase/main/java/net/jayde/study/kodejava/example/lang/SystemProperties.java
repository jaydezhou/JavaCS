package net.jayde.study.kodejava.example.lang;

import java.util.Properties;

public class SystemProperties {
    public static void main(String[] args) {
        //
        // Get System properties
        //
        Properties properties = System.getProperties();

        //
        // Get the path separator which is unfortunatelly
        // using a different symbol in different OS platform.
        //
        String pathSeparator =
                properties.getProperty("path.separator");
        System.out.println("pathSeparator = " + pathSeparator);
    }
}
