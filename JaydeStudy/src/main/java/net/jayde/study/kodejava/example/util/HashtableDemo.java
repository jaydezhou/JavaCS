package org.kodejava.example.util;

import java.util.Enumeration;
import java.util.Hashtable;

public class HashtableDemo {
    public static void main(String[] args) {
        //
        // Creates an instance of Hashtable
        //
        Hashtable<String, Integer> numbers =
                new Hashtable<String, Integer>();
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);

        // 
        // Returns an enumeration of the keys in this Hashtable
        //
        Enumeration<String> keys = numbers.keys();
        while (keys.hasMoreElements()) {
            // 
            // Returns the next element of this enumeration if this
            // enumeration object has at least one more element to 
            // provide
            String key = keys.nextElement();
            System.out.println("Key: " + key + ", Value: " +
                    numbers.get(key));
        }
    }
}
