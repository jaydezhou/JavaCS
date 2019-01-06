package com.jeffrey.pattern.command;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by wei on 15/5/4.
 */
public class Human {

    public void work() {
        System.out.println("work");
    }

    public void sleep() {
        System.out.println("sleep");
    }

    public void eat() {
        System.out.println("eat");
    }
HashMap<String,Date> a;
}
