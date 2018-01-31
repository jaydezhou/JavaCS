package net.jayde.study.kodejava.example.fundamental;

public class SignedLeftShiftOperator {
    public static void main(String[] args) {
        //
        // The binary representation of 2 is "0010"
        //
        int number = 2;
        System.out.println("number      = " + number);
        System.out.println("binary      = " +
                Integer.toBinaryString(number));

        //
        // Using the shift left operator we shift the bits two
        // times to the left. Which will shift the "0010" into
        // "1000"
        //
        int shiftedLeft = number << 2;
        System.out.println("shiftedLeft = " + shiftedLeft);
        System.out.println("binary      = " +
                Integer.toBinaryString(shiftedLeft));
    }
}
