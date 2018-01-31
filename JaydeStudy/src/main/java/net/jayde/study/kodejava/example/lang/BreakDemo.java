package net.jayde.study.kodejava.example.lang;

public class BreakDemo {
    public static void main(String[] args) {
        int[] numbers = {5, 3, 6, 9, 8, 7, 4, 2, 1, 10};

        int index;
        boolean found = false;

        int search = 7;
        for (index = 0; index < numbers.length; index++) {
            if (numbers[index] == search) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println(search + " found at index " + index);
        } else {
            System.out.println(search + " was not found.");
        }

        start:
        while (true) {
            for (int y = 0; y < 10; y++) {
                System.out.print("y = " + y + "; ");
                if (y == 5) {
                    System.out.println("");
                    break start;
                }
            }
        }
    }
}
