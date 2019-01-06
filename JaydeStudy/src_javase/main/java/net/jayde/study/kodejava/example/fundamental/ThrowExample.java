package net.jayde.study.kodejava.example.fundamental;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThrowExample {
    public static void main(String[] args) {
        try {
            ThrowExample.throwException();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ThrowExample.printDate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void throwException() {
        int x = 6;
        int[] y = {3, 2, 1, 0};

        for (int i = 0; i < y.length; i++) {
            if (y[i] == 0) {
                //
                // Throws an ArithmeticException when about to
                // divide by zero.
                //
                String message = String.format(
                        "x = %d; y = %d; a division by zero.",
                        x, y[i]);
                throw new ArithmeticException(message);
            } else {
                int z = x / y[i];
                System.out.println("z= " + z);
            }
        }
    }

    public static void printDate(Date date) {
        if (date == null) {
            throw new NullPointerException("Date cannot be null.");
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Date: " + df.format(date));
    }
}
