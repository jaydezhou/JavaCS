package net.jayde.study.kodejava.example.lang;

public class PalindromChecker {

    //
    // This method checks the string for palindrome. We use StringBuilder to
    // reverse the original string.
    //
    private boolean isPalindrome(String text) {
        System.out.println("Original text = " + text);

        String reverse = new StringBuilder(text).reverse().toString();
        System.out.println("Reverse text  = " + reverse);

        //
        // Compare the original text with the reverse one and ignoring its case
        //
        return text.equalsIgnoreCase(reverse);
    }

    public static void main(String[] args) {
        String text = "Sator Arepo Tenet Opera Rotas";

        PalindromChecker checker = new PalindromChecker();
        System.out.println("Is Palindrom: " + checker.isPalindrome(text));
    }
}
