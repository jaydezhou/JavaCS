package org.kodejava.example.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class DefaultLocaleExample {
    public static void main(String[] args) {
        //
        // Use Random class to generate some random number
        //
        Random random = new Random();

        //
        // We use the system default locale to format a number.
        //
        NumberFormat formatter = new DecimalFormat();
        Locale locale = Locale.getDefault();
        System.out.println("Locale = " + locale);
        System.out.println(formatter.format(random.nextDouble()));

        //
        // We change the default locale to ITALY locale by setting it through the
        // Locale.setDefault() method and then we format another number using a
        // new locale number. This change we'll affect all the class that aware
        // to the Locale, such as the NumberFormat class.
        //
        locale = Locale.ITALY;
        Locale.setDefault(locale);
        formatter = new DecimalFormat();
        System.out.println("Locale = " + locale);
        System.out.println(formatter.format(random.nextDouble()));
    }
}
