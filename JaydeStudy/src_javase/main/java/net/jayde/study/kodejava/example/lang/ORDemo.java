package net.jayde.study.kodejava.example.lang;

public class ORDemo {
    public static void main(String[] args) {
        int numberA = 16;
        int numberB = 4;

        //
        // Operator "|" is used for doing bitwise OR operation
        //
        int result = numberA | numberB;

        System.out.println(numberA + " | " + numberB + " = " + result);

        //
        // Print the result in binary format
        //
        System.out.println(Integer.toBinaryString(numberA) +
                " | " + Integer.toBinaryString(numberB) +
                " = " + Integer.toBinaryString(result));
    }
}
