package net.jayde.study.kodejava.example.lang;

public class ObtainingSuperClass {
    public static void main(String[] args) {
        //
        // Create an instance of String class
        //
        Object o = new String("Hello");

        //
        // Get String class super class
        //
        Class clazz = o.getClass().getSuperclass();
        System.out.println("Super Class = " + clazz);

        //
        // Create an instance of StringIndexOutOfBoundsException class
        //
        o = new StringIndexOutOfBoundsException("Error message");

        //
        // Get StringIndexOutOfBoundsException class super class
        //
        clazz = o.getClass().getSuperclass();
        System.out.println("Super Class = " + clazz);
    }
}
