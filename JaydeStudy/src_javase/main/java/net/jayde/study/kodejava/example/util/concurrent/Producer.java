package org.kodejava.example.util.concurrent;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    BlockingQueue<String> queue;

    Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("Producer.run");
        String[] data = {"D001", "D002", "D003", "D004", "D005"};

        try {
            for (String element : data) {
                queue.put(element);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}