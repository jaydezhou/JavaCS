package net.jayde.study.kodejava.example.fundamental;

import java.util.Date;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.System.out;

public class StaticImport {
    public static void main(String[] args) {
        //
        // Using static field PI and static method abs() from the
        // java.lang.Math class.
        //
        double circle = PI * 10;
        int absolute = abs(-100);

        //
        // Using static field of the java.lang.System class to
        // print out the current date.
        //
        out.println("Today: " + new Date());
    }
}
