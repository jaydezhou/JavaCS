package org.kodejava.example.util;

import java.util.Arrays;

public class ArraySearchExample {
    public static void main(String[] args) {
        // 
        // We create an array of ints where the search will be done.
        //
        int items[] = {5, 4, 6, 1, 8, 9, 6, 7, 3, 2};

        //
        // Prior to search we need to sort the array items to the 
        // correct order, we can call the Arrays.sort() method to 
        // do this. If we did not sort it the result will be undefined, 
        // the search process will return a negative result.
        //
        Arrays.sort(items);

        //
        // To search we use Arrays.binarySearch() methods which accept
        // parameters of any array type. The search we passed the array
        // to be searched and the key to be searched for.
        //
        // When the search item exist more than one in the array, this
        // method gives no guarantee which one will be returned.
        //
        int index = Arrays.binarySearch(items, 1);

        //
        // Print out where the 7 number is located in the array.
        //
        System.out.println("Search item found at: " + index);
    }
}
