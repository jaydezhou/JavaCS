package org.kodejava.example.util;

import java.util.Locale;

public class LocaleCountryLanguageCode {
    public static void main(String[] args) {

        //
        // Gets an array of all installed locales. The returned
        // array represents the union of locales supported by the
        // Java runtime environment and by installed
        // LocaleServiceProvider implementations.
        //
        Locale[] locales = Locale.getAvailableLocales();

        StringBuilder sb = new StringBuilder();
        for (Locale locale : locales) {
            sb.append("Locale name: ").append(locale.getDisplayName())
                    .append(" = ")
                    .append(locale.getLanguage()).append("_")
                    .append(locale.getCountry()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
