package net.jayde.study.kodejava.example.lang;

public class StringSplit {
    public static void main(String[] args) {
        String data = "1,Diego Maradona,Footballer,Argentina";
        String[] items = data.split(",");
        for (String item : items) {
            System.out.println("item = " + item);
        }
    }
}
