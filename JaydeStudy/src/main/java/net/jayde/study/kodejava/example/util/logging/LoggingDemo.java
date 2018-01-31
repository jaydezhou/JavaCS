package org.kodejava.example.util.logging;

import java.util.logging.Logger;

public class LoggingDemo {
    public static void main(String[] args) {
        //
        // Obtaining an instance of Logger. This will create a new Logger
        // is it doesn't exist.
        //
        Logger log = Logger.getLogger(LoggingDemo.class.getName());

        //
        // Log some message using a different type of severity leve.
        //
        log.info("Info Message");
        log.warning("Warning Message");
        log.severe("Severe Message");
        log.config("Config Message");
        log.fine("Fine Message");
        log.finer("Finer Message");
        log.finest("Finest Message");
    }
}
