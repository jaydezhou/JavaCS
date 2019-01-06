package net.jayde.study.kodejava.example.fundamental;

public class ReturnDemo {

    public static void main(String[] args) {
        int z = ReturnDemo.calculate(2, 3);
        System.out.println("z = " + z);

        Dog dog = new Dog("Spaniel", "Doggie");
        System.out.println(dog.getDog());
    }

    public static int calculate(int x, int y) {
        //
        // return an int type value
        //
        return x + y;
    }

    public void print() {
        System.out.println("void method");

        //
        // it does not need to contain a return statement, but it
        // may do so
        //
        return;
    }

    public String getString() {
        return "return String type value";

        //
        // try to execute a statement after return a value will
        // cause a compile-time error.
        //
        // String error = "error";
    }
}

class Dog {
    private String breed;
    private String name;

    Dog(String breed, String name) {
        this.breed = breed;
        this.name = name;
    }

    public Dog getDog() {
        //
        // return Dog type
        //
        return this;
    }

    public String toString() {
        return "breed: " + breed.concat("name: " + name);
    }
}
