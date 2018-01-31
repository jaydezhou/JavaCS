package net.jayde.study.kodejava.example.io;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;

public class LineNumberReaderExample {
    public static void main(String[] args) throws Exception {
        File file = null;
        FileReader fr = null;
        LineNumberReader lnr = null;

        try {
            // 
            // We'll read a file called student.csv that contains our
            // student information data.
            //
            file = new File("student.csv");

            // 
            // To create the FileReader we can pass in our student data 
            // file to the reader. Next we pass the reader into our 
            // LineNumberReader class.
            //
            fr = new FileReader(file);
            lnr = new LineNumberReader(fr);

            // 
            // If we set the line number of the LineNumberReader here 
            // we'll got the line number start from the defined line 
            // number + 1
            //

            //lnr.setLineNumber(400);

            String line = "";
            while ((line = lnr.readLine()) != null) {
                // 
                // We print out the student data and show what line 
                // is currently read by our program.
                //
                System.out.println("Line Number " + lnr.getLineNumber() +
                        ": " + line);
            }
        } finally {
            // 
            // Don't forget to close the stream when we finish reading 
            // the file.
            //
            if (fr != null) {
                fr.close();
            }
            if (lnr != null) {
                lnr.close();
            }
        }
    }
}
