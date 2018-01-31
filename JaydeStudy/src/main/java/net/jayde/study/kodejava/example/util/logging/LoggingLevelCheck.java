package org.kodejava.example.util.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingLevelCheck {
    public static void main(String[] args) {
        //
        // Obtains an instance of Logger and set the logging level to 
        // Level.WARNING.
        // 
        Logger log = Logger.getLogger(LoggingLevelCheck.class.getName());
        log.setLevel(Level.WARNING);

        //
        // Log INFO level message. This message will not be logged due to the
        // log level set to Level.WARNING
        //
        if (log.isLoggable(Level.INFO)) {
            log.info("Application Information Message");
        }

        //
        // Log WARNING level message when Level.WARNING is loggable.
        //
        if (log.isLoggable(Level.WARNING)) {
            log.warning("Application Warning Information");
        }

        //
        // Log SEVERE level message when Level.SEVERE is loggable.
        //
        if (log.isLoggable(Level.SEVERE)) {
            log.severe("Information Severe Information");
        }
    }
}
