package net.jayde.study.kodejava.example.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreateObjectDemo {
    public static void main(String[] args) {
        Class clazz = String.class;

        try {
            Constructor constructor = clazz.getConstructor(new Class[]{String.class});

            String object = (String) constructor.newInstance(new Object[]{"Hello World!"});
            System.out.println("String = " + object);

            constructor = clazz.getConstructor(new Class[]{StringBuilder.class});
            object = (String) constructor.newInstance(new Object[]{new StringBuilder("Hello Universe!")});
            System.out.println("String = " + object);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
