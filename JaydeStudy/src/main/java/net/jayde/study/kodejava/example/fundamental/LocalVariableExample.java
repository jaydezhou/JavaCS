package net.jayde.study.kodejava.example.fundamental;

public class LocalVariableExample {
    //
    // it's okay if total variable does not initialize.
    // it will initialize with default value = 0.
    //
    int total;

    public static int add() {
        //
        // this will cause compile-time error if does not initialize
        //
        int x = 1, y = 2;

        //
        // z is assigned by the calculation result of x + y
        //
        int z = x + y;
        return z;
    }

    public static void main(String[] args) {
        LocalVariableExample lve = new LocalVariableExample();
        //
        // assigns total with the result of add() method execution
        //
        lve.total = add();
        System.out.println("total= " + lve.total);
    }
}
