
package net.jayde.study.kodejava.example.lang.reflect;

import java.io.Serializable;

public class IsInterfaceDemo {
    public static void main(String[] args) {
        IsInterfaceDemo.get(Serializable.class);
        IsInterfaceDemo.get(Long.class);
    }

    private static void get(Class clazz) {
        if (clazz.isInterface()) {
            System.out.println(clazz.getName() +
                    " is an interface type.");
        } else {
            System.out.println(clazz.getName() +
                    " is not an interface type.");
        }
    }
}
