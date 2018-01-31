
package net.jayde.study.kodejava.example.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileRead {
    public static void main(String[] args) {
        try {
            String path = "C:\\Temp\\source.txt";
            FileInputStream fis = new FileInputStream(path);
            FileChannel fileChannel = fis.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(64);

            int bytesRead = fileChannel.read(buffer);
            while (bytesRead != -1) {
                System.out.println("Read: " + bytesRead);
                buffer.flip();

                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());                    
                }
                
                buffer.clear();
                bytesRead = fileChannel.read(buffer);
            }

            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
