package net.jayde.study.kodejava.example.fundamental;

public class LanguageDemo {
    public static void main(String[] args) {
        Language language = new English();
        System.out.println(language.getBirthday());
        System.out.println(language.getGreeting());

        language = new Indonesian();
        System.out.println(language.getBirthday());
        System.out.println(language.getGreeting());
    }
}
