package org.kodejava.example.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListIterator {
    public static void main(String[] args) {
        List<String> grades = new LinkedList<String>();
        grades.add("A");
        grades.add("B");
        grades.add("C");
        grades.add("D");
        grades.add("E");

        //
        // Retrieve objects in LinkedList using Iterator.
        //
        Iterator iterator = grades.iterator();
        while (iterator.hasNext()) {
            System.out.println("Grade: " + iterator.next());
        }
    }
}
