package net.jayde.study.kodejava.example.lang;

public class BooleanValueOfExample {
    public static void main(String[] args) {
        boolean b = true;
        Boolean bool = Boolean.valueOf(b);
        System.out.println("bool = " + bool);

        // Here we test the conversion, which is likely unnecessary. But
        // here is shown the boolean true is equals to Boolean.TRUE static
        // variable and of course you can guest the boolean false value is
        // equals to Boolean.FALSE
        if (bool.equals(Boolean.TRUE)) {
            System.out.println("bool = " + bool);
        }

        String s = "false";

        // On the line below we convert a string to Boolean, it returns
        // true if and only if the string is equals to "true" otherwise it
        // returns false
        Boolean bools = Boolean.valueOf(s);
        System.out.println("bools = " + bools);

        String f = "abc";
        Boolean abc = Boolean.valueOf(f);
        System.out.println("abc = " + abc);
    }
}
