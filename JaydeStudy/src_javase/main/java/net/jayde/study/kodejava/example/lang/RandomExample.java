package net.jayde.study.kodejava.example.lang;

public class RandomExample {
    public static void main(String[] args) {
        // The Math.random() returns a random number between 0.0 and 1.0 
        // including 0.0 but not including 1.0.
        double number = Math.random();
        System.out.println("Generated number: " + number);

        // By multiplying Math.random() result with a number will give
        // us a range of random number between, for instance 0.0 to 10.0 as
        // shown in the example below.
        number = Math.random() * 10;
        System.out.println("Generated number: " + number);

        // To get a random number fron n to m we can add a n value to the 
        // Math.random() as the lowest number and then multiply it with the
        // the highest number. The example below creates random number
        // between 100.0 and 200.0.
        number = 100 + (Math.random() * 100);
        System.out.println("Generated number: " + number);

        // Creates an integer random number
        int random = 100 + (int) (Math.random() * 100);
        System.out.println("Generated number: " + random);
    }
}
