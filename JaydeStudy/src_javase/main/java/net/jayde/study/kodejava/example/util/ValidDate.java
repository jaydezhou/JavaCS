package org.kodejava.example.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidDate {
    public static void main(String[] args) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        //
        // Input to be parsed should strictly follow the defined date format
        // above.
        //
        format.setLenient(false);

        String date = "12/18/2007";
        try {
            format.parse(date);
        } catch (ParseException e) {
            System.out.println("Date " + date + " is not valid according to " +
                    ((SimpleDateFormat) format).toPattern() + " pattern.");
        }
    }
}
