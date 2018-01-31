package net.jayde.study.kodejava.example.fundamental;

public class ReferenceDemo {
    public static void main(String[] args) {
        //
        // declaration of reference variable
        //
        Reference ref1, ref2;

        // 
        // ref3 is declared final, it will cause it can reassign 
        // or refer to different object
        //
        final Reference ref3;

        // 
        // assign ref1 with object Reference
        //
        ref1 = new Reference("This is the first reference variable", 1);

        //
        // access method getNumber() of object Reference through 
        // variable ref1
        //
        int number = ref1.getNumber();
        System.out.println("number= " + number);

        // 
        // assign ref2 with object Reference
        //
        ref2 = new Reference("This is the second reference variable", 2);

        //
        // passing ref2 as method parameter of printText() method
        //
        ReferenceDemo.printText(ref2);

        //
        // assign ref3 with object Reference
        //
        ref3 = new Reference("This is the third reference variable", 3);

        //
        // try to reassign ref3 will cause a compile-time error
        //
        //ref3 = new Reference("Try to reassign",3);

    }

    public static void printText(Reference reference) {
        String text = reference.getText();
        System.out.println(text);
    }
}

class Reference {
    private int number;
    private String text;

    Reference(String text, int number) {
        this.text = text;
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public int getNumber() {
        return number;
    }
}
