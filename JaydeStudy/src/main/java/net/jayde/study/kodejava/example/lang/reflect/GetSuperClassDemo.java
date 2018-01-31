
package net.jayde.study.kodejava.example.lang.reflect;

import javax.swing.*;
import java.util.Date;

public class GetSuperClassDemo {
    public static void main(String[] args) {
        GetSuperClassDemo.get(String.class);
        GetSuperClassDemo.get(Date.class);
        GetSuperClassDemo.get(JButton.class);
        GetSuperClassDemo.get(Timer.class);
    }

    public static void get(Class clazz) {
        //
        // Gets array of direct interface of clazz object
        //
        Class[] interfaces = clazz.getInterfaces();

        System.out.format("Direct Interfaces of %s:%n",
                clazz.getName());
        for (Class clz : interfaces) {
            System.out.println(clz.getName());
        }

        //
        // Gets direct superclass of clazz object
        //
        Class superclz = clazz.getSuperclass();
        System.out.format("Direct Superclass of %s: is %s %n",
                clazz.getName(), superclz.getName());
        System.out.println("====================================");
    }
}
