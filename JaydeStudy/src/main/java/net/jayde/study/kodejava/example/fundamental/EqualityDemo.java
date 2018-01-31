package net.jayde.study.kodejava.example.fundamental;

public class EqualityDemo {
    public static void main(String[] args) {
        int value1 = 10, value2 = 10, number1 = 10;
        char a = 'a', b = 'b';
        double number2 = 10d;
        Cat kitty = new Cat("Kitty");
        Cat kitten = new Cat("Kitty");
        Cat sweetie = kitty;

        if (value1 == value2) {
            System.out.println("Equal");
        }

        if (a != b) {
            System.out.println("Not Equal");
        }

        //
        // though it have different type, but it have same value
        //
        if (number1 == number2) {
            System.out.println("Equal");
        }

        //
        // it's not refer to the same object, so it will return
        // false
        //
        if (kitty == kitten) {
            System.out.format("(kitty==kitten), the result is " +
                    (kitty == kitten));
        } else {
            System.out.println("(kitty==kitten), the result is " +
                    (kitty == kitten));
        }

        //
        // it's refer to the same object, so it will return true
        //
        if (kitty == sweetie) {
            System.out.println("(kitty==sweetie), the result is " +
                    (kitty == sweetie));
        }

        if (true != false) {
            System.out.println("true!=false");
        }

    }
}

class Cat {
    private String name;

    Cat(String name) {
        this.name = name;
    }
}
