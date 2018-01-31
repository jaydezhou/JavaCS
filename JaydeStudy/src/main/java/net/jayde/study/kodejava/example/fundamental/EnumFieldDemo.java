package net.jayde.study.kodejava.example.fundamental;

enum Fruit {
    APPLE(1.5f), ORANGE(2), MANGGO(3.5f), GRAPE(5);

    private float price;

    Fruit(float price) {
        System.out.println("Name: " + this.name() + " initialized.");
        this.price = price;
    }

    public float getPrice() {
        return this.price;
    }
}

public class EnumFieldDemo {
    public static void main(String[] args) {
        //
        // Get the name and price of all enum constant value.
        //
        for (Fruit f : Fruit.values()) {
            System.out.println("Fruit = " + f.name() + "; Price = " + f.getPrice());
        }
    }
}
