package net.jayde.study.kodejava.example.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@net.jayde.study.kodejava.example.annotation.HelloAnno(value = "Hello", greetTo = "Universe")
public class GettingAnnotation {
    public static void main(String[] args) {
        GettingAnnotation demo = new GettingAnnotation();

        Class clazz = demo.getClass();
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation anno : annotations) {
            System.out.println("Annotation Type: " + anno.annotationType());
        }

        net.jayde.study.kodejava.example.annotation.HelloAnno anno = (net.jayde.study.kodejava.example.annotation.HelloAnno) clazz.getAnnotation(net.jayde.study.kodejava.example.annotation.HelloAnno.class);
        System.out.println("Anno Value  : " + anno.value());
        System.out.println("Anno GreetTo: " + anno.greetTo());

        try {
            Method m = clazz.getMethod("sayHi");

            anno = m.getAnnotation(net.jayde.study.kodejava.example.annotation.HelloAnno.class);
            System.out.println("Anno Value  : " + anno.value());
            System.out.println("Anno GreetTo: " + anno.greetTo());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        demo.sayHello();
    }

    @net.jayde.study.kodejava.example.annotation.HelloAnno(value = "Hi", greetTo = "Alice")
    public void sayHi() {
    }

    @net.jayde.study.kodejava.example.annotation.HelloAnno(value = "Hello", greetTo = "Bob")
    public void sayHello() {
        try {
            Method m = getClass().getMethod("sayHello");
            net.jayde.study.kodejava.example.annotation.HelloAnno anno = m.getAnnotation(net.jayde.study.kodejava.example.annotation.HelloAnno.class);

            System.out.println(anno.value() + " " + anno.greetTo());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
