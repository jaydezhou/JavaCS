package org.kodejava.example.util;

import java.util.Calendar;
import java.util.Date;

public class DateCompareBefore {
    public static void main(String[] args) {
        //
        // Get current date
        //
        Date today = new Date();

        //
        // Substract 1 day from the current date.
        //
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();

        //
        // Tests if this date is before the specified date. This method will
        // return true if the value time represented by the yesterday object
        // is ealier than today.
        //
        if (yesterday.before(today)) {
            System.out.println(yesterday + " is before " + today);
        }
    }
}
