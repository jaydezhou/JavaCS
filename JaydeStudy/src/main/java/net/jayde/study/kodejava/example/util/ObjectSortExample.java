package org.kodejava.example.util;

import java.util.Arrays;

public class ObjectSortExample {
    public static void main(String[] args) {
        //
        // Here we have a list of people name, it is balinese people
        // name.
        //
        String names[] = {"Wayan", "Made", "Nyoman", "Ketut"};

        //
        // We can order them by their name and write to the screen. 
        // It's just like sorting a primitive array.
        //
        Arrays.sort(names);
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            System.out.print("name = " + name + "; ");
        }
        System.out.println("");

        //
        // Now we will sort an array of our own object. It is a bit
        // different compared to sorting an array of primitives. The
        // first rule is we need our object to implements the 
        // Comparable interface. This interface have one contract we
        // need to implement, the compareTo() contract. See the Person 
        // class below.
        //
        // Here we create four Person objects, and set their name.
        // After that we sort the Person object based on their name 
        // using the Arrays.sort() method.
        //
        Person persons[] = new Person[4];
        persons[0] = new Person("Wayan");
        persons[1] = new Person("Made");
        persons[2] = new Person("Nyoman");
        persons[3] = new Person("Ketut");
        Arrays.sort(persons);

        for (int i = 0; i < persons.length; i++) {
            Person person = persons[i];
            System.out.println("person = " + person);
        }
    }
}

//
// As mentioned above that to be sortable by Arrays.sort() method our
// class need to implements the compareTo(Object obj) method which is
// a contract defined in the Comparable interface.
//
class Person implements Comparable {
    private String name;

    //
    // A constructor to Person class.
    //
    public Person(String name) {
        this.name = name;
    }

    //
    // Here is our compareTo implementation, here we just delegate
    // the compareTo to the string object compareTo method. The basic
    // rule for this method to return are: 0 when objects value are
    // equals; 1 if this object value is greater; and -1 if this object
    // value is smaller.
    //
    public int compareTo(Object o) {
        Person p = (Person) o;
        return this.name.compareTo(p.name);
    }

    //
    // Override toString() to return a readable person name.
    //
    public String toString() {
        return name;
    }
}
