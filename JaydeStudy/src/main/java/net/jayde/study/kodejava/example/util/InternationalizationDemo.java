package org.kodejava.example.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class InternationalizationDemo {

    public static void main(String[] args) {
        //
        // Load resource bundle for Locale.UK locale. The resource 
        // bundle will load the MessagesBundle_en_GB.properties file.
        //
        ResourceBundle bundle =
                ResourceBundle.getBundle("MessagesBundle", Locale.UK);
        System.out.println("Message in " + Locale.UK + ": " +
                bundle.getString("greeting"));

        //
        // Change the default locale to Indonesian and get the default 
        // resource bundle for the current locale.
        //
        Locale.setDefault(new Locale("in", "ID"));
        bundle = ResourceBundle.getBundle("MessagesBundle");
        System.out.println("Message in " + Locale.getDefault() + ": " +
                bundle.getString("greeting"));
    }
}
