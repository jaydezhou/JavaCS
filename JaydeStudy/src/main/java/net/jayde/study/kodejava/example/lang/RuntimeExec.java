package net.jayde.study.kodejava.example.lang;

import java.io.IOException;

public class RuntimeExec {
    public static void main(String[] args) {
        String command = "explorer.exe";

        try {
            Process process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
