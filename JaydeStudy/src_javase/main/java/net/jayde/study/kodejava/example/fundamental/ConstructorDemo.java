package net.jayde.study.kodejava.example.fundamental;

public class ConstructorDemo {
    private String arg;
    private int x;
    private int y;

    public ConstructorDemo() {
    }

    public ConstructorDemo(String arg) {
        this.arg = arg;
    }

    public ConstructorDemo(int x) {
        this.x = x;
    }

    public ConstructorDemo(int x, int y) {
        this.y = y;
    }
}

class RunConstructor {
    public static void main(String[] args) {
        // 
        // Change the default constructor from private to public in
        // the ConstructorDemo class above then call the statement 
        // below. It will create an instance object cons0 without 
        // any error.
        //
        ConstructorDemo cons0 = new ConstructorDemo();

        // 
        // Change the default constructor back to private, then call 
        // the statement below. ConstructorDemo() is not visible 
        // because it declared as private.
        //
        ConstructorDemo cons1 = new ConstructorDemo();

        // invoke Constructor(String arg)
        ConstructorDemo cons2 = new ConstructorDemo("constructor");

        // invoke public Constructor(int x)
        ConstructorDemo cons3 = new ConstructorDemo(1);

        //invoke Constructor(int x, int y)
        ConstructorDemo cons4 = new ConstructorDemo(1, 2);
    }

}
