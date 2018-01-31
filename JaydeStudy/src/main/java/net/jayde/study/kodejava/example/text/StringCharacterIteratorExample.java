package net.jayde.study.kodejava.example.text;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class StringCharacterIteratorExample {
    /*
     * We are going to iterate this famous phrase
     */
    private static final String text =
            "The quick brown fox jumps over the lazy dog";

    public static void main(String[] args) {
        CharacterIterator it = new StringCharacterIterator(text);

        int vowels = 0;
        int consonants = 0;

        //
        // Iterates charater sets from the beginning to the last character
        //
        for (char ch = it.first(); ch != CharacterIterator.DONE; ch = it.next()) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowels = vowels + 1;
            } else if (ch != ' ') {
                consonants = consonants + 1;
            }
        }

        System.out.println("Number of vowels: " + vowels);
        System.out.println("Number of consonants: " + consonants);
    }
}
