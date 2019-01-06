package net.jayde.study.kodejava.example.fundamental;

public class NegationOperator {
    public static void main(String[] args) {
        //
        // negate the result of boolean expressions
        //
        boolean negate = !(2 < 3);
        boolean value = !false;

        System.out.println("result: " + negate);
        System.out.println("value : " + value);
    }
}
