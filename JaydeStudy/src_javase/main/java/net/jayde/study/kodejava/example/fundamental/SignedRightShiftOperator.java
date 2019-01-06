package net.jayde.study.kodejava.example.fundamental;

public class SignedRightShiftOperator {
    public static void main(String[] args) {
        //
        // The binary representation of 32 is
        // "00000000000000000000000000100000"
        //
        int number = 32;
        System.out.println("number       = " + number);
        System.out.println("binary       = " +
                Integer.toBinaryString(number));

        //
        // Using the shift right operator we shift the bits
        // four times to the right which resulting the result
        // of "00000000000000000000000000000010"
        //
        int shiftedRight = number >> 4;
        System.out.println("shiftedRight = " + shiftedRight);
        System.out.println("binary       = " +
                Integer.toBinaryString(shiftedRight));
    }
}
