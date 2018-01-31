package net.jayde.study.kodejava.example.annotation;

@HelloAnno(value = "Good Morning", greetTo = "Universe")
public class HelloAnnoExample {
    public static void main(String[] args) {
        HelloAnnoExample example = new HelloAnnoExample();

        example.sayHi();
        example.sayHello();
    }

    @HelloAnno(value = "Hi there", greetTo = "Alice")
    public void sayHi() {
    }

    @HelloAnno(value = "Hello there", greetTo = "Bob")
    public void sayHello() {
    }
}
