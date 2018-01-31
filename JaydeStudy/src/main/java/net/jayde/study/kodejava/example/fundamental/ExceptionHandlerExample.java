package net.jayde.study.kodejava.example.fundamental;

public class ExceptionHandlerExample {

    public static void main(String[] args) {
        int x = 1, y = 0, z = 0;

        try {
            //
            // divide by 0 will thrown an exception
            //
            z = ExceptionHandlerExample.divide(x, y);
            System.out.println("z = " + z);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Finally block is always executed.");
        }
    }

    /**
     * Divide the given first number by the second number.
     *
     * @param x the first number.
     * @param y the second number.
     * @return the result of division.
     * @throws RuntimeException when an exception occurs.
     */
    private static int divide(int x, int y) throws RuntimeException {
        return x / y;
    }
}
