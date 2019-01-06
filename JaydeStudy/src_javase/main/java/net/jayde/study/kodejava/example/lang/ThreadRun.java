package net.jayde.study.kodejava.example.lang;

public class ThreadRun implements Runnable {

    public void run() {
        System.out.println("Running..");
    }

    public static void main(String[] args) {
        //
        // Instantiate ThreadRun
        //
        ThreadRun tr = new ThreadRun();

        //
        // Create instance of Thread and passing ThreadRun object
        // as argument.
        //
        Thread thread = new Thread(tr);

        //
        // By passing Runnable object, it tells the
        // thread to use run() of Runnable object.
        //
        thread.start();
    }
}
