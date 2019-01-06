package net.jayde.study.kodejava.example.lang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessResult {
    public static void main(String[] args) {
        try {
            Process process = Runtime.getRuntime().exec("ls -al");

            //
            // Wait for this process to finish or terminated
            //
            process.waitFor();

            //
            // Get process exit value
            //
            int exitValue = process.exitValue();
            System.out.println("exitValue = " + exitValue);

            //
            // Read the result of the executer ls -al command by reading the
            // process's input stream
            //
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
