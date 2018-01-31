package net.jayde.study.kodejava.example.fundamental;

enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

public class EnumValueOfTest {
    public static void main(String[] args) {
        //
        // Using valueOf() method we can get an enum constant whose
        // value corresponds to the string passed as the parameter.
        //
        Day day = Day.valueOf("SATURDAY");
        System.out.println("Day = " + day);
        day = Day.valueOf("WEDNESDAY");
        System.out.println("Day = " + day);

        try {
            //
            // The following line will produce an exception because the
            // enum type does not contains a constant named JANUARY.
            //
            day = Day.valueOf("JANUARY");
            System.out.println("Day = " + day);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
