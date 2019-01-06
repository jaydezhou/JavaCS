package net.jayde.study.kodejava.example.lang;

import java.util.Calendar;
import java.util.Date;

public class ClassInterfaces {
    public static void main(String[] args) {
        //
        // Get an instance of Date class
        //
        Date date = Calendar.getInstance().getTime();

        //
        // Get all interfaces implemented by the java.util.Date class and
        // print their names.
        //
        Class[] interfaces = date.getClass().getInterfaces();
        for (Class intf : interfaces) {
            System.out.println("Interface Name = " + intf.getName());
        }

        //
        // For the primitive tipe the interface will be an empty array.
        //
        Class c = char.class;
        interfaces = c.getInterfaces();
        for (Class intf : interfaces) {
            System.out.println("Interface Name = " + intf.getName());
        }
    }
}
