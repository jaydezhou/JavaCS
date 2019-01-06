package org.kodejava.example.util.logging;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConditionalLogging {
    private Logger logger = Logger.getLogger(ConditionalLogging.class.getName());

    public static void main(String[] args) {
        ConditionalLogging demo = new ConditionalLogging();
        demo.executeMethod();
    }

    //
    // In this method we will check if the Logger level is equals to
    // Level.INFO before we do the real logging. This will minimize
    // the impact of logging if in the next time we increate the level
    // to Level.WARNING or Level.SEVERE so that these message will not
    // logged anymore.
    //
    public void executeMethod() {
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Entering executeMethod() at : " + new Date());
        }

        //
        // Method body
        //
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(i * j + " ");
            }
            System.out.println("");
        }

        if (logger.isLoggable(Level.INFO)) {
            logger.info("Exiting executeMethod() at  : " + new Date());
        }
    }
}
