package org.kodejava.example.util;

import java.util.*;

public class SetToList {
    public static void main(String[] args) {
        //
        // Create a Set and add some objects into the Set.
        //
        Set<Object> set = new HashSet<Object>();
        set.add("A");
        set.add(new Long(10));
        set.add(new Date());

        //
        // Convert the Set to a List can be done by passing the Set instance into
        // the constructor of a List implementation class such as ArrayList.
        //
        List<Object> list = new ArrayList<Object>(set);
        for (int i = 0; i < list.size(); i++) {
            Object o = list.get(i);
            System.out.println("Object = " + o);
        }
    }
}
