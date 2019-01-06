package net.jayde.study.kodejava.example.fundamental;

public abstract class Animal {
    private String species;

    public Animal(String species) {
        this.species = species;
    }

    public abstract void makeASound();

    public String getSpecies() {
        return species;
    }

    public static void main(String[] args) {
        Animal pig = new Pig("Warthog");
        pig.makeASound();
    }
}
