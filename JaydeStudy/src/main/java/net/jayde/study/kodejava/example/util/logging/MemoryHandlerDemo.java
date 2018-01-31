package org.kodejava.example.util.logging;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.MemoryHandler;

public class MemoryHandlerDemo {
    public static void main(String[] args) {
        MemoryHandlerDemo demo = new MemoryHandlerDemo();
        try {
            demo.testMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testMethod() throws Exception {
        Logger logger = Logger.getLogger(MemoryHandlerDemo.class.getName());
        FileHandler fileHandler = new FileHandler("myapp.log");

        //
        // Create a MemoryHandler that will dump the log messages for the 
        // latest 100 records when a Level.SEVERE log is logged to by the
        // Logger class.
        //
        MemoryHandler memoryHandler = new MemoryHandler(fileHandler, 100, Level.SEVERE);
        logger.addHandler(memoryHandler);

        //
        // Write some messages to the log
        //
        logger.info("Information message");
        logger.warning("Warning message");

        //
        // This causes the log messages dump to the file log.
        //
        logger.severe("Severe message");
    }
}
