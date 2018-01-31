package net.jayde.study.kodejava.example.fundamental;

public class Pig extends Animal {

    public Pig(String species) {
        super(species);
    }

    @Override
    public void makeASound() {
        System.out.println("oink oink");
    }
}
