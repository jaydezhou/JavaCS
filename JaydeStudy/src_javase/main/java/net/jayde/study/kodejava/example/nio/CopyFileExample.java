
package net.jayde.study.kodejava.example.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;

public class CopyFileExample
{
    // This program is an example how we use the New I/O API in Java
    // programming. This API is located in the java.nio.*, this requires
    // at least Java 1.4 because this API was first included in this
    // version.
    // The java.nio is a block based io processing, instead of a stream
    // base io which is the old version io processing in Java.
    public static void main(String[] args) throws Exception
    {
        // The source file name where data will be copied from
        String source = "medical-report.txt";
        // The destination file name where data will be copied to
        String destination = "medical-report-final.txt";

        // Create an instance of FileInputStream to read the source
        // content
        FileInputStream fis = new FileInputStream(source);
        // Create an instance of FileOutputStream to write the data
        // into destination file
        FileOutputStream fos = new FileOutputStream(destination);

        // Get the FileChannel of FileInputStream instance
        FileChannel fci = fis.getChannel();
        // Get the FileChannel of FileOutputStream instance
        FileChannel fco = fos.getChannel();

        // Create a buffer with 1024 size for buffering data
        // while copying from source file to destination file.
        // To create the buffer here we used a static method
        // ByteBuffer.allocate()
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // Here we start to read the source file and write it
        // to the destionation file. We repeat this process
        // until the read method of input stream channel return
        // nothing (-1).
        while(true)
        {
            // read a block of data and put it in the buffer
            int read = fci.read(buffer);

            // did we reach the end of the channel? if yes
            // jump out the while-loop
            if (read == -1)
                break;

            // flip the buffer
            buffer.flip();

            // write to the destination channel
            fco.write(buffer);
            
            // clear the buffer and user it for the next read
            // process
            buffer.clear();
        }
    }
}
