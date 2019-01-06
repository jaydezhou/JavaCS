package net.jayde.study.kodejava.example.lang;

public class TernaryOperatorDemo {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        //
        // Get the maximum value
        //
        int min = a < b ? a : b;

        //
        // The use of ternary operator above is an alternative
        // of the following if-then-else statement.
        //
        int minValue;
        if (a < b) {
            minValue = a;
        } else {
            minValue = b;
        }

        //
        // Get the minimum value.
        //
        int max = a > b ? a : b;

        //
        // Get the absolute value.
        //
        int abs = a < 0 ? -a : a;

        System.out.println("min      = " + min);
        System.out.println("minValue = " + minValue);
        System.out.println("max      = " + max);
        System.out.println("abs      = " + abs);
    }
}
