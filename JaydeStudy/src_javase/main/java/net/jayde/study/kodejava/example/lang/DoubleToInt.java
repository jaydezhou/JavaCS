package net.jayde.study.kodejava.example.lang;

public class DoubleToInt {
    public static void main(String[] args) {
        Double numberA = 49.99;
        System.out.println("numberA = " + numberA);

        //
        // Converting Double value to int value by calling
        // the Double.intValue() method.
        //
        int numberB = numberA.intValue();
        System.out.println("numberB = " + numberB);

        //
        // Converting Double value to int value by casting
        // the primitive double value of the Double instance
        //
        int numberC = (int) numberA.doubleValue();
        System.out.println("numberC = " + numberC);

        double numberD = 99.99;
        System.out.println("numberD = " + numberD);

        //
        // Converting double value into int value using
        // type casting
        // 
        int numberE = (int) numberD;
        System.out.println("numberE = " + numberE);
    }
}
