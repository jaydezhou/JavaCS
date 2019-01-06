
package net.jayde.study.kodejava.example.nio;

import java.nio.LongBuffer;

public class BufferClear {
    public static void main(String[] args) {
        //
        // Allocates a new LongBuffer.
        //
        LongBuffer buffer = LongBuffer.allocate(64);

        //
        // Write the long array into the buffer.
        //
        buffer.put(new long[] {10, 20, 30, 40, 50, 60, 70, 80});
        System.out.println("Position: " + buffer.position());
        System.out.println("Limit   : " + buffer.limit());
        
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

        //
        // clear() method set the position to zero and limit
        // to the capacity of the buffer.
        //
        buffer.clear();
        System.out.println("Position: " + buffer.position());
        System.out.println("Limit   : " + buffer.limit());
    }
}
