package org.kodejava.example.util.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueShutdown {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(32);

        MyDataProducer producer = new MyDataProducer(queue);
        MyDataConsumer consumer = new MyDataConsumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
