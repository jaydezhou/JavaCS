package org.kodejava.example.util.concurrent;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {
    public static void main(String[] args) {
        final BlockingQueue<String> queue = new LinkedBlockingQueue<>(1024);

        //
        // Producer Tread
        //
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String data = UUID.randomUUID().toString();
                        System.out.printf("[%s] PUT [%s].%n",
                                Thread.currentThread().getName(), data);
                        queue.put(data);
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Producer").start();

        //
        // Consumer-1 Thread
        //
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String data = queue.take();
                        System.out.printf("[%s] GET [%s].%n",
                                Thread.currentThread().getName(), data);
                        Thread.sleep(550);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Consumer-1").start();

        //
        // Consumer-2 Thread
        //
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String data = queue.take();
                        System.out.printf("[%s] GET [%s].%n",
                                Thread.currentThread().getName(), data);
                        Thread.sleep(750);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Consumer-2").start();
    }
}
