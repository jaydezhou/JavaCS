
package net.jayde.study.kodejava.example.math;

import java.math.BigDecimal;

public class BigDecimalOperation {
    public static void main(String[] args) {
        BigDecimal decimalA = new BigDecimal("98765432123456789");
        BigDecimal decimalB = new BigDecimal("10");

        decimalA = decimalA.add(decimalB);
        System.out.println("decimalA = " + decimalA);

        decimalA = decimalA.multiply(decimalB);
        System.out.println("decimalA = " + decimalA);

        decimalA = decimalA.subtract(decimalB);
        System.out.println("decimalA = " + decimalA);

        decimalA = decimalA.divide(decimalB);
        System.out.println("decimalA = " + decimalA);

        decimalA = decimalA.pow(2);
        System.out.println("decimalA = " + decimalA);

        decimalA = decimalA.negate();
        System.out.println("decimalA = " + decimalA);
    }
}
