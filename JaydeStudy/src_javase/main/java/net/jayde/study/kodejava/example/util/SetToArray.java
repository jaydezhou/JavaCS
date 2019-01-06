package org.kodejava.example.util;

import java.util.*;

public class SetToArray {
    public static void main(String[] args) {
        //
        // Create a Set and add some objects into the Set.
        //
        Set<Object> set = new HashSet<Object>();
        set.add("A");
        set.add(new Long(10));
        set.add(new Date());

        //
        // Converting Set into array can be done by creating a List from
        // the Set and from the List we can convert it into array by calling
        // the toArray() method.
        //
        List<Object> list = new ArrayList<Object>(set);
        Object[] objects = list.toArray();

        for (int i = 0; i < objects.length; i++) {
            Object object = objects[i];
            System.out.println("Object = " + object);
        }
    }
}
