package net.jayde.study.kodejava.example.fundamental;

enum Month {
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER
}

public class EnumValuesTest {
    public static void main(String[] args) {
        //
        // values() method return an array that contains a list of the
        // enumeration constants.
        //
        Month[] months = Month.values();
        System.out.println("Month size: " + months.length);

        //
        // We can user for each statement to print each enumeration
        // constant.
        //
        for (Month month : Month.values()) {
            System.out.println("Month: " + month);
        }
    }
}
