package net.jayde.study.kodejava.example.lang;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StackTraceToString {
    public static void main(String[] args) {
        try {
            int a = 10 / 0;
        } catch (Exception e) {
            //
            // Create a StringWriter and a PrintWriter both of these object
            // will be used to convert the data in the stack trace to a string.
            //
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);

            //
            // Instead of writting the stack trace in the console we write it
            // to the PrintWriter, to get the stack trace message we then call
            // the toString() method of StringWriter.
            //
            e.printStackTrace(pw);

            System.out.println("Error = " + sw.toString());
        }
    }
}
