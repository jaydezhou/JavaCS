package net.jayde.study.kodejava.example.fundamental;

public class ThreeWheelsBike extends Bike {
    @Override
    public void moveForward() {
        super.moveForward();
        System.out.println("Three Wheels Bike: Move Forward.");
    }

    public static void main(String[] args) {
        Bike bike = new ThreeWheelsBike();
        bike.moveForward();
    }
}
