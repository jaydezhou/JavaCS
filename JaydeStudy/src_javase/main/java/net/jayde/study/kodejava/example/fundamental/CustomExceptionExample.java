package net.jayde.study.kodejava.example.fundamental;

public class CustomExceptionExample {
    public static void main(String[] args) {
        int x = 1, y = 0;

        try {
            int z = CustomExceptionExample.divide(x, y);
            System.out.println("z = " + z);
        } catch (DivideByZeroException e) {
            e.printStackTrace();
        }

    }

    public static int divide(int x, int y)
            throws DivideByZeroException {
        try {
            return (x / y);
        } catch (ArithmeticException e) {
            String m = x + " / " + y + ", trying to divide by zero";
            throw new DivideByZeroException(m, e);
        }
    }
}

class DivideByZeroException extends Exception {
    DivideByZeroException() {
    }

    DivideByZeroException(String message) {
        super(message);
    }

    DivideByZeroException(String message, Throwable cause) {
        super(message, cause);
    }
}
