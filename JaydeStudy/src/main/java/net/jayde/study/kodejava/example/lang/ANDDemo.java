package net.jayde.study.kodejava.example.lang;

public class ANDDemo {
    public static void main(String[] args) {
        int numberA = 16;
        int numberB = 16;

        //
        // Operator "&"  is used for doing bitwise AND operation
        //
        int result = numberA & numberB;

        System.out.println(numberA + " & " + numberB + " = " + result);

        //
        // Print the result in binary format
        //
        System.out.println(Integer.toBinaryString(numberA) +
                " & " + Integer.toBinaryString(numberB) +
                " = " + Integer.toBinaryString(result));
    }
}
