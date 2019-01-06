package net.jayde.study.kodejava.example.fundamental;

public class StaticDemo {
    // static variable
    static int x = 12;

    // static variable with final value that never change
    final static int Y = 20;

    // non-static variable
    int z;

    public static void main(String[] args) {
        StaticDemo sd0 = new StaticDemo();

        System.out.println("x before update = " + StaticDemo.x);
        System.out.println("y= " + StaticDemo.Y);

        sd0.z = StaticDemo.x + StaticDemo.Y;
        System.out.println("z= " + sd0.z);

        StaticDemo.x = 15;
        System.out.println("x after update = " + StaticDemo.x);

        StaticDemo sd1 = new StaticDemo();
        StaticDemo sd2 = new StaticDemo();
        StaticDemo.x = 20;

        System.out.println("StaticDemo.x = " + StaticDemo.x);
        System.out.println("sd0 = " + sd0.getX());
        System.out.println("sd1 = " + sd1.getX());
        System.out.println("sd2 = " + sd2.getX());

        //
        // try to assign value to final variable, it will cause a
        // compile time error
        //
        // StaticDemo.Y = 30;
    }

    public int getX() {
        return StaticDemo.x;
    }
}
