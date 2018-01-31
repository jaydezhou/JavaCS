package org.kodejava.example.util.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteLogToFile {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(WriteLogToFile.class.getName());

        //
        // Create an instance of FileHandler that write log to a file called
        // app.log. Each new message will be appended at the at of the log file.
        //
        FileHandler fileHandler = new FileHandler("app.log", true);
        logger.addHandler(fileHandler);

        if (logger.isLoggable(Level.INFO)) {
            logger.info("Information message");
        }

        if (logger.isLoggable(Level.WARNING)) {
            logger.warning("Warning message");
        }

    }
}
