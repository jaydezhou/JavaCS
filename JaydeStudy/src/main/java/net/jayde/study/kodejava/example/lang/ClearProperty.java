package net.jayde.study.kodejava.example.lang;

public class ClearProperty {
    public static void main(String[] args) {
        String key = "user.dir";

        //
        // The System.clearProperty() method available in Java 1.5
        //
        System.clearProperty(key);
    }
}
