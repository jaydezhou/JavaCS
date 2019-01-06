package net.jayde.study.kodejava.example.text;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class StringShortWithCollator {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<String>();
        fruits.add("Guava");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");
        fruits.add("Apple");

        //
        // Define a collator for US English.
        //
        Collator collator = Collator.getInstance(Locale.US);

        //
        // Sort the list base on the collator
        //
        Collections.sort(fruits, collator);

        for (int i = 0; i < fruits.size(); i++) {
            String fruit = fruits.get(i);

            System.out.println("Fruit = " + fruit);
        }
    }
}
