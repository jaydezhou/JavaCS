package net.jayde.study.kodejava.example.fundamental;

import java.io.File;
import java.io.IOException;

public class ExceptionExample {
    public static void main(String[] args) {
        //
        // You must catch the checked exception otherwise you get a
        // compile time error.
        //
        try {
            ExceptionExample.checkFileSize("data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //
        // The unchecked exception doesn't requires you to catch
        // it and it doesn't produce a compile time error.
        //
        ExceptionExample.divide();
    }

    /**
     * This method throws a Checked Exception, so it must declare the
     * Exception in its method declaration
     *
     * @param fileName given file name
     * @throws IOException when the file size is to large.
     */

    public static void checkFileSize(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.length() > Integer.MAX_VALUE) {
            throw new IOException("File is too large.");
        }
    }

    /**
     * This method throws a RuntimeException.
     * There is no need to declare the Exception in method declaration
     *
     * @return a division result.
     * @throws ArithmeticException when arithmetic exception occurs.
     */
    public static int divide() throws ArithmeticException {
        int x = 1, y = 0;
        return x / y;
    }
}
