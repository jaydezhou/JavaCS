package net.jayde.study.kodejava.example.fundamental;

public class MultipleCatchExample {
    public static void main(String[] args) {
        int[] numbers1 = {1, 2, 3, 4, 5};
        int[] numbers2 = {1, 2, 3, 4, 5, 6};

        try {
            //
            // This line throws an ArrayIndexOutOfBoundsException
            //
            MultipleCatchExample.printResult(numbers1);

            //
            // This line throws an ArithmeticException
            //
            MultipleCatchExample.printResult(numbers2);
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
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
     */
    private static int divide(int x, int y) {
        return x / y;
    }

    /**
     * Print the output result of divide operation by calling the
     * divide() method.
     *
     * @param numbers integer arrays of the divided number
     * @throws ArrayIndexOutOfBoundsException when an exception
     *                                        occurs.
     */
    private static void printResult(int[] numbers) {
        int x, z, y = 1;
        for (int i = 0; i < 6; i++) {
            x = numbers[i];
            if (i == 5) {
                y = 0;
            }
            z = MultipleCatchExample.divide(x, y);
            System.out.println("z = " + z);
        }
    }
}
