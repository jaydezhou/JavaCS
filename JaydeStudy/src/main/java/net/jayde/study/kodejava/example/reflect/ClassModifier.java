package net.jayde.study.kodejava.example.reflect;

import java.lang.reflect.Modifier;

public class ClassModifier {
    public static void main(String[] args) {
        getClassModifier(String.class);
        getClassModifier(TestA.class);
        getClassModifier(TestB.class);
    }

    private static void getClassModifier(Class clazz) {
        int modifier = clazz.getModifiers();

        //
        // Return true if the integer argument includes the public modifier,
        // false otherwise.
        //
        if (Modifier.isPublic(modifier)) {
            System.out.println(clazz.getName() + " class modifier is public");
        }

        //
        // Return true if the integer argument includes the protected modifier,
        // false otherwise.
        //
        if (Modifier.isProtected(modifier)) {
            System.out.println(clazz.getName() + " class modifier is protected");
        }

        //
        // Return true if the integer argument includes the private modifier,
        // false otherwise.
        //
        if (Modifier.isPrivate(modifier)) {
            System.out.println(clazz.getName() + " class modifier is private");
        }

        //
        // Return true if the integer argument includes the static modifier,
        // false otherwise.
        //
        if (Modifier.isStatic(modifier)) {
            System.out.println(clazz.getName() + " class modifier is static");
        }

        //
        // Return true if the integer argument includes the final modifier,
        // false otherwise.
        //
        if (Modifier.isFinal(modifier)) {
            System.out.println(clazz.getName() + " class modifier is final");
        }

        //
        // Return true if the integer argument includes the abstract modifier,
        // false otherwise.
        //
        if (Modifier.isAbstract(modifier)) {
            System.out.println(clazz.getName() + " class modifier is abstract");
        }
    }

    protected static final class TestA {
    }

    private abstract class TestB {
    }
}
