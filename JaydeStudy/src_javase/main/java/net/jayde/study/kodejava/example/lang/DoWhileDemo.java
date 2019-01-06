package net.jayde.study.kodejava.example.lang;

public class DoWhileDemo {
    public static void main(String[] args) {
        int i = 0;

        //
        // The do-while statement executes at least once because
        // the expression is checked at the end of the loop
        // process.
        //
        do {
            //
            // This block will be executed while i is smaller or
            // equals to 10.
            //
            System.out.println(i);
            i++;
        } while (i <= 10);
    }
}
