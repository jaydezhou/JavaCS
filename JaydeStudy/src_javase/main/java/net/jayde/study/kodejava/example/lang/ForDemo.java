package net.jayde.study.kodejava.example.lang;

public class ForDemo {
    public static void main(String[] args) {
        //
        // Do a loop from 0 to 10.
        //
        for (int i = 0; i <= 10; i++) {
            System.out.println("i = " + i);
        }

        //
        // Loop through all the array items.
        //
        int[] numbers = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int number : numbers) {
            System.out.println("number = " + number);
        }
    }
}
