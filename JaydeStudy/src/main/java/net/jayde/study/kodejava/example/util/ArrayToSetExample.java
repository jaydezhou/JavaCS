package org.kodejava.example.util;

import java.util.*;

public class ArrayToSetExample {
    public static void main(String[] args) {
        Integer[] numbers = {7, 7, 8, 9, 10, 8, 8, 9, 6, 5, 4};

        //
        // To convert an array into a Set first we convert it to a List. Next
        // with the list we create a HashSet and pass the list as the constructor.
        //
        List list = Arrays.asList(numbers);
        Set set = new HashSet(list);

        //
        // Display what we get in the set.
        //
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Object o = iterator.next();
            System.out.print(o + ", ");
        }
    }
}
