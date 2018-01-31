package net.jayde.study.kodejava.example.lang;

public class JavaHomeDirectory {
    public static void main(String[] args) {
        String javaHome = System.getProperty("java.home");
        System.out.println("javaHome = " + javaHome);
    }
}
