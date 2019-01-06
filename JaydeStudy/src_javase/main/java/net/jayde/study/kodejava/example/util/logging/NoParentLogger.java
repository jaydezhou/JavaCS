package org.kodejava.example.util.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class NoParentLogger {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(NoParentLogger.class.getName());

        //
        // Do not forward any log messages the the logger parent handlers.
        //
        logger.setUseParentHandlers(false);

        //
        // Specify a ConsoleHanlder for this logger instance.
        //
        logger.addHandler(new ConsoleHandler());
        logger.info("Logging an information message.");
    }
}
