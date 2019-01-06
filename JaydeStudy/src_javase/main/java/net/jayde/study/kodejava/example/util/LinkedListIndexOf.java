package org.kodejava.example.util;

import java.util.LinkedList;
import java.util.List;

public class LinkedListIndexOf {
    public static void main(String[] args) {
        List<String> names = new LinkedList<String>();
        names.add("Alice");
        names.add("Bob");
        names.add("Carol");
        names.add("Mallory");

        //
        // Search for Carol using the indexOf method. This method
        // returns the index of the object when found. If not found
        // -1 will be returned.
        //
        int index = names.indexOf("Carol");
        System.out.println("Index = " + index);

        //
        // We can check to see if the index returned is in the range
        // of the LinkedList element size.
        //
        if (index > 0 && index < names.size()) {
            String name = names.get(index);
            System.out.println("Name  = " + name);
        }
    }
}
