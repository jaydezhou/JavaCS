package org.kodejava.example.util.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogConsoleHandler {
    public static void main(String[] args) {
        //
        // Obtains Logger instance
        //
        Logger logger = Logger.getLogger(LogConsoleHandler.class.getName());

        //
        // Add ConsoleHandler to Logger.
        //
        ConsoleHandler consoleHandler = new ConsoleHandler();
        logger.addHandler(consoleHandler);

        if (logger.isLoggable(Level.INFO)) {
            logger.info("This is information message for testing ConsoleHandler");
        }
    }
}
