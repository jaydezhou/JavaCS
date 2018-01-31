package net.jayde.study.kodejava.example.lang;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NumberOccurrenceInArray {
    public static void main(String[] args) {
        int[] numbers = new int[]{1, 8, 3, 4, 3, 2, 5, 7, 3, 1, 4, 5, 6, 4, 3};

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            int key = numbers[i];
            if (map.containsKey(key)) {
                int occurrence = map.get(key);
                occurrence++;
                map.put(key, occurrence);
            } else {
                map.put(key, 1);
            }
        }

        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            int key = (Integer) iterator.next();
            int occurrence = map.get(key);

            System.out.println(key + " occur " + occurrence + " time(s).");
        }
    }
}
