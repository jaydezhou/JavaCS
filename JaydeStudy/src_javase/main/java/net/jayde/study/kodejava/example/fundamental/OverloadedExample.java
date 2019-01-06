package net.jayde.study.kodejava.example.fundamental;

public class OverloadedExample {
    public void print(Object object) {
        System.out.println("object = " + object);
    }

    public void print(String string) {
        System.out.println("string = " + string);
    }

    public void print(int number) {
        System.out.println("number = " + number);
    }

    public void print(float number) {
        System.out.println("number = " + number);
    }

    public void print(double number) {
        System.out.println("number = " + number);
    }
}
