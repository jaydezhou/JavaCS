package net.jayde.study.kodejava.example.fundamental;

public class CarDemo {
    public static void main(String[] args) {
        Car car = new Car();
        car.setBrand("Honda");
        System.out.println("Brand = " + car.getBrand());

        //
        // The setBrand() and getBrand() is inherited from the Car
        // class.
        //
        Truck truck = new Truck();
        truck.setBrand("Ford");
        System.out.println("Brand = " + truck.getBrand());
        truck.getLoadCapacity();

        //
        // The setBrand(), getBrand() and setNumberOfSeat methods
        // are is inherited from the Car class.
        //
        Sedan sedan = new Sedan();
        sedan.setBrand("Hyundai");
        System.out.println("Brand = " + sedan.getBrand());
        sedan.setNumberOfSeat(2);
        sedan.getGearType();
    }
}
