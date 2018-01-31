package net.jayde.study.kodejava.example.reflect;

import java.lang.reflect.Constructor;

public class GetConstructors {
    public static void main(String[] args) {
        Class clazz = String.class;

        //
        // Get all declared contructors and iterate the constructors to get their
        // name and parameter types.
        //
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            String name = constructor.getName();
            System.out.println("Constructor name= " + name);

            Class[] paramterTypes = constructor.getParameterTypes();
            for (Class c : paramterTypes) {
                System.out.println("Param type name = " + c.getName());
            }
            System.out.println("----------------------------------------");
        }

        //
        // Getting a specific constructor of the java.lang.String
        //
        try {
            Constructor constructor = String.class.getConstructor(new Class[]{String.class});
            System.out.println("Constructor     = " + constructor.getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
