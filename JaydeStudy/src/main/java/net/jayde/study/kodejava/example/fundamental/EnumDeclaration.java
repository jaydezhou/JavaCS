package net.jayde.study.kodejava.example.fundamental;

public class EnumDeclaration {
    public static void main(String[] args) {
        //
        // Creating an enum variable declaration and assignment. Because the
        // produced is of type Producer we can only assign value defined by
        // enumeration.
        //
        Producer producer = Producer.APPLE;

        if (producer == Producer.LENOVO) {
            System.out.println("Produced by Lenovo.");
        } else {
            System.out.println("Produced by others.");
        }
    }
}
