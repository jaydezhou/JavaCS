package org.kodejava.example.util.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogLevelSetting {
    //
    // Obtains a Logger instance, it will create one if it is not already exist.
    //
    private static Logger logger = Logger.getLogger(LogLevelSetting.class.getName());

    public static void main(String[] args) {
        //
        // Set the log level to Level.INFO, the severe message will be logged.
        //
        logger.setLevel(Level.INFO);
        logger.severe("This message will be logged.");

        //
        // Set the log level to Level.SEVERE, the warning message will not be
        // logged as Level.WARNING is smaller than Level.SEVERE
        //
        logger.setLevel(Level.SEVERE);
        logger.warning("This message won't be logged.");

        //
        // Turn of the log, no message will be logged.
        //
        logger.setLevel(Level.OFF);
        logger.info("All log is turned off.");

        //
        // Turn the logger back on, this will result all the corresponding
        // logger message below will be logged.
        //
        logger.setLevel(Level.ALL);
        logger.info("Information message.");
        logger.warning("Warning message.");
        logger.severe("Severe message.");
    }
}
