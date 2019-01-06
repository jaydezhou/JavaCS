package org.kodejava.example.util.concurrent;

import java.util.concurrent.BlockingQueue;

public class MyDataConsumer implements Runnable {
    BlockingQueue<String> queue;

    public MyDataConsumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("MyDataConsumer.run");

        while (true) {
            try {
                String element = queue.take();
                if ("DONE".equals(element)) {
                    System.out.println("Exiting consumer thread, " +
                            "end of data reached.");
                    break;
                }
                System.out.println("Element = " + element);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
