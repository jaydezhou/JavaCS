package net.jayde.study.kodejava.example.lang;

public class ContinueDemo {
    public static void main(String[] args) {
        int[] numbers = {5, 11, 3, 9, 12, 15, 4, 7, 6, 17};
        int length = numbers.length;
        int counter = 0;

        for (int i = 0; i < length; i++) {
            //
            // When number is greater or equals to 10 skip the
            // current loop and continue to the next loop because
            // we only interested to count number less than 10.
            //
            if (numbers[i] >= 10) {
                continue;
            }

            counter++;
        }

        System.out.println("Found " + counter + " numbers less than 10.");

        //
        // The example below used a labeled continue statement. In the 
        // loop below we sum the number in the array until reminder of 
        // the number divided by 2 equals to zero. If the reminder is 
        // zero we skip to the next dimension of the array.
        //
        int[][] data = {
                {8, 2, 1},
                {3, 3},
                {3, 4, 5},
                {5, 4},
                {6, 5, 2}};
        int total = 0;

        outer:
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] % 2 == 0) {
                    continue outer;
                }

                total += data[i][j];
            }
        }

        System.out.println("Total = " + total);
    }
}
