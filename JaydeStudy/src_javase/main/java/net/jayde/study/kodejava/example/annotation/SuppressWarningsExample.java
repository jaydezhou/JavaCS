package net.jayde.study.kodejava.example.annotation;

import java.util.Date;

public class SuppressWarningsExample {
    @SuppressWarnings(value = {"deprecation"})
    public static void main(String[] args) {
        Date date = new Date(2008, 9, 30);

        System.out.println("date = " + date);
    }
}

