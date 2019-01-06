package net.jayde.study.kodejava.example.lang;

public class EqualsIgnoreCase {
    public static void main(String[] args) {
        String uppercase = "ABCDEFGHI";
        String mixed = "aBCdEFghI";

        //
        // To compare two string equality regarding it case use the
        // String.equalsIgnoreCase method.
        //

        if (uppercase.equalsIgnoreCase(mixed)) {
            System.out.println("Uppercase and Mixed equals.");
        }
    }
}
