package org.kodejava.example.util;

import java.util.SortedSet;
import java.util.TreeSet;

public class FirstSetElement {
    public static void main(String[] args) {
        SortedSet<String> set = new TreeSet<String>();
        set.add("One");
        set.add("Two");
        set.add("Three");
        set.add("Four");
        set.add("Five");

        //
        // SortedSet orders the items it contains. After that we get the first
        // item from the set using first() method. The fist item will be "Five".
        //
        String firstItem = set.first();
        System.out.println("firstItem = " + firstItem);
    }
}
