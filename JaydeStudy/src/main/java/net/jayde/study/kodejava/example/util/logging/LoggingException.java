package org.kodejava.example.util.logging;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingException {
    private static Logger logger = Logger.getLogger(LoggingException.class.getName());

    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);

        try {
            //
            // Try to parsing a wrong date.
            //
            Date date = df.parse("12/30/1990");

            System.out.println("Date = " + date);
        } catch (ParseException e) {
            //
            // Create a Level.SEVERE logging message
            //
            if (logger.isLoggable(Level.SEVERE)) {
                logger.log(Level.SEVERE, "Error parsing date", e);
            }
        }
    }
}
