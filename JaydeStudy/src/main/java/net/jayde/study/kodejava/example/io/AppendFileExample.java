package net.jayde.study.kodejava.example.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AppendFileExample {
    public static void main(String[] args) {
        File file = new File("user.txt");

        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("username=kodejava;password=secret"
                    + System.getProperty("line.separator"));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
