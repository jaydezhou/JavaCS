
package net.jayde.study.kodejava.example.lang.reflect;

import java.util.Date;

public class ClassNameDemo {
    public static void main(String[] args) {
        Date date = new Date();

        //
        // Gets the Class of the date instance.
        //
        Class clazz = date.getClass();

        //
        // Gets the name of the class.
        //
        String name = clazz.getName();
        System.out.println("Class name     : " + name);

        //
        // Gets the canonical name of the class.
        //
        String canonical = clazz.getCanonicalName();
        System.out.println("Canonical name : " + canonical);

        //
        // Gets the simple name of the class.
        //
        String simple = clazz.getSimpleName();
        System.out.println("Simple name    : " + simple);
    }
}
