package net.jayde.study.kodejava.example.lang;

import java.io.Console;

public class IfThenElseDemo {
    public static void main(String[] args) {
        //
        // Get an instance of system console for taking user
        // input.
        //
        Console c = System.console();

        int score = 0;
        String grade;

        System.out.print("Please enter your score: ");

        try {
            //
            // Take user score input and convert the input
            // value into number.
            //
            score = Integer.valueOf(c.readLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (score >= 90) {
            grade = "A";
        } else if (score >= 80) {
            grade = "B";
        } else if (score >= 60) {
            grade = "C";
        } else if (score >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        System.out.println("Grade = " + grade);
    }
}
