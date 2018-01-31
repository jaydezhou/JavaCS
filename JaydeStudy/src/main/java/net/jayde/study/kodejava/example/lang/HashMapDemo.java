package net.jayde.study.kodejava.example.lang;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        Map errors = new HashMap();

        // mapping some data in the map
        errors.put("404", "Resource not found.");
        errors.put("403", "Access forbidden.");
        errors.put("500", "General server error.");

        // reading data from the map
        String errorDesc = (String) errors.get("404");
        System.out.println("Error 404: " + errorDesc);

        // iterating the map by it's keys
        Iterator iterator = errors.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            System.out.println("Error " + key + " means " + errors.get(key));
        }
    }
}
