package org.kodejava.example.util;

import java.util.LinkedList;
import java.util.List;

public class LinkedListToArray {
    public static void main(String[] args) {
        List<String> list = new LinkedList<String>();
        list.add("Blue");
        list.add("Green");
        list.add("Purple");
        list.add("Orange");

        //
        // Converting LinkedList to array can be done by calling the toArray()
        // method of the List;
        //
        String[] colors = new String[list.size()];
        list.toArray(colors);

        for (int i = 0; i < colors.length; i++) {
            System.out.println("color = " + colors[i]);
        }
    }
}
