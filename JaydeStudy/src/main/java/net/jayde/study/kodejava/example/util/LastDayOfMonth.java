package org.kodejava.example.util;

import java.util.Calendar;

public class LastDayOfMonth {
    public static void main(String[] args) {
        //
        // Get a calendar instance
        //
        Calendar calendar = Calendar.getInstance();

        //
        // Get the last date of the current month. To get the last date for a
        // specific month you can set the calendar month using calendar object
        // calendar.set(Calendar.MONTH, theMonth) method.
        //
        int lastDate = calendar.getActualMaximum(Calendar.DATE);

        //
        // Set the calendar date to the last date of the month so then we can
        // get the last day of the month
        //
        calendar.set(Calendar.DATE, lastDate);
        int lastDay = calendar.get(Calendar.DAY_OF_WEEK);

        //
        // Print the current date and the last date of the month
        //
        System.out.println("Last Date: " + calendar.getTime());

        //
        // The lastDay will be in a value from 1 to 7 where 1 = monday and 7 =
        // saturday respectively.
        //
        System.out.println("Last Day : " + lastDay);
    }
}
