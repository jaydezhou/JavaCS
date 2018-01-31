package org.kodejava.example.util.logging;

import java.io.IOException;
import java.util.logging.*;

public class LogFormatter {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(LogFormatter.class.getName());

        try {
            //
            // Create a FileHanlder that will log to mylog.txt with a
            // SimpleFormatter.
            //
            FileHandler simpleHandler = new FileHandler("mylog.txt", true);
            simpleHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(simpleHandler);

            //
            // Create a ConsoleHanlder that will log to the console with
            // an XMLFormatter.
            //
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new XMLFormatter());
            logger.addHandler(consoleHandler);

            //
            // Do not sent the message to parent handlers. 
            //
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Fail to create logger file handler.", e);
        }

        logger.info("Logging application information message.");
        logger.warning("Logging application warning message.");
    }
}
