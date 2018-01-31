package net.jayde.study.kodejava.example.lang;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SystemEnv {
    public static void main(String[] args) {
        // We get the environment information from the System class. 
        // The getenv method (why shouldn't it called getEnv()?) 
        // returns a map that will never have null keys or values 
        // returned.
        Map map = System.getenv();

        // For this purpose of example we will interate the keys and 
        // values in it so we create an iterator object from it.
        Set keys = map.keySet();
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()) {
            // Here we iterate based on the keys inside the map, and 
            // with the key in hand we can get it values.
            String key = (String) iterator.next();
            String value = (String) map.get(key);

            System.out.println(key + " = " + value);
        }
    }
}
