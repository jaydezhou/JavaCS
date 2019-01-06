package net.jayde.study.kodejava.example.lang;

public class CheckForInterface {
    public static void main(String[] args) {
        //
        // Checking whether Cloneable is an interface or class
        //
        Class clazz = Cloneable.class;
        boolean isInterface = clazz.isInterface();
        System.out.println("Is Interface = " + isInterface);

        //
        // Checking whether String is an interface or class
        //
        clazz = String.class;
        isInterface = clazz.isInterface();
        System.out.println("Is Interface = " + isInterface);
    }
}
