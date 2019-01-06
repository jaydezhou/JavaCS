package net.jayde.study.kodejava.example.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface HelloAnno {
    String value();

    String greetTo();
}
