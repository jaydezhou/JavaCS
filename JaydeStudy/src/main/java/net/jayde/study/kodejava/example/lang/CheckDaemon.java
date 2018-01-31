package net.jayde.study.kodejava.example.lang;

public class CheckDaemon implements Runnable {
    public CheckDaemon() {
    }

    public void run() {
        System.out.println("Running [" +
                Thread.currentThread().getName() + "]");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new CheckDaemon(), "FirstThread");
        Thread t2 = new Thread(new CheckDaemon(), "SecondThread");

        t1.setDaemon(true);
        t1.start();
        t2.start();

        if (t1.isDaemon()) {
            System.out.format("%s is a daemon thread.%n",
                    t1.getName());
        } else {
            System.out.format("%s is a user thread.%n",
                    t1.getName());
        }

        if (t2.isDaemon()) {
            System.out.format("%s is a daemon thread %n",
                    t2.getName());
        } else {
            System.out.format("%s is a user thread %n",
                    t2.getName());
        }
    }
}
