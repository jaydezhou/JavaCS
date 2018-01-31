package net.jayde.study.kodejava.example.lang;

public class WhileDemo {
    public static void main(String[] args) {
        //
        // Start the count down from 10
        //
        int countDown = 10;

        //
        // Do the count down process while the value of
        // countDown is bigger or equals to zero.
        //
        while (countDown >= 0) {
            System.out.println(countDown);
            countDown--;

            try {
                //
                // Adds one second delay.
                //
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
