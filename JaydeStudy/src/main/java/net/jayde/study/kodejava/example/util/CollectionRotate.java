package org.kodejava.example.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionRotate {
    public static void main(String[] args) {
        List numbers = new ArrayList();

        // Add some items into the collection
        for (int i = 0; i < 25; i++) {
            numbers.add(i);
        }

        // Print the collection items        
        System.out.println(Arrays.toString(numbers.toArray()));

        // Rotates the elements in the collection by the 10.
        Collections.rotate(numbers, 10);

        // Print the rotated collection items
        System.out.println(Arrays.toString(numbers.toArray()));
    }
}
