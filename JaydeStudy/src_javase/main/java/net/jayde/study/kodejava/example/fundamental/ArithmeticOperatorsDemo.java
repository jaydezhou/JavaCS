package net.jayde.study.kodejava.example.fundamental;

public class ArithmeticOperatorsDemo {
    public static void main(String[] args) {
        int result = 5 + 4;  // result = 9
        System.out.println("result = " + result);

        result = result - 2; // result = 7
        System.out.println("result = " + result);

        result = result * 4; // result = 28
        System.out.println("result = " + result);

        result = result / 7; // result = 4
        System.out.println("result = " + result);

        result = result % 3; // result = 1
        System.out.println("result = " + result);

        //
        // Combining the arithmetic operators with a simple assignment
        // operators give us a compound assignment. We can write the
        // operation above in the following form. But as you can see
        // the above snippets is easier to read.
        //
        result = 5 + 4; // result = 9
        System.out.println("result = " + result);

        result -= 2;    // result = 7
        System.out.println("result = " + result);

        result *= 4;    // result = 28
        System.out.println("result = " + result);

        result /= 7;    // result = 4
        System.out.println("result = " + result);

        result %= 3;    // result = 1
        System.out.println("result = " + result);
    }
}
