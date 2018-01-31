package net.jayde.study.kodejava.example.lang;

public class SubstringExample {
    public static void main(String[] args) {
        // This program demonstrate how we can take some part of a string or
        // the what we called as substring. Java String class provides substring
        // method with some overloaded parameter.

        String sentence = "Lorem ipsum dolor sit amet...";

        // The first substring method with single parameter beginIndex will
        // take some part of the string from the begining index until the last
        // character in the string.
        String part = sentence.substring(6);
        System.out.println("Part of sentence: " + part);

        // The second substring method take two parameters, beginIndex and 
        // endIndex. This method returns the substring start from beginIndex to
        // the endIndex.
        part = sentence.substring(12, 21);
        System.out.println("Part of sentence: " + part);
    }
}
