package org.kodejava.example.util.concurrent;

import java.util.concurrent.BlockingQueue;

public class MyDataProducer implements Runnable {
    BlockingQueue<String> queue;

    public MyDataProducer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("MyDataProducer.run");
        String[] data = {"D001", "D002", "D003", "D004", "D005", "DONE"};

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
