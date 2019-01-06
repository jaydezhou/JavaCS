
package net.jayde.study.kodejava.example.nio;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileCopy {
    public static void main(String[] args) {
        //
        // Define the source and target file
        //
        File source = new File("C:\\Temp\\source.txt");
        File target = new File("C:\\Temp\\target.txt");

        FileChannel sourceChannel = null;
        FileChannel targetChannel = null;

        try {
            //
            // Create the source and target channel
            //
            sourceChannel = new FileInputStream(source).getChannel();
            targetChannel = new FileOutputStream(target).getChannel();

            //
            // Copy data from the source channel into the target 
            // channel
            //
            targetChannel.transferFrom(sourceChannel, 0, 
                    sourceChannel.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //
                // Close the source and target channel
                //
                if (sourceChannel != null) {
                    sourceChannel.close();
                }
                if (targetChannel != null) {
                    targetChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
