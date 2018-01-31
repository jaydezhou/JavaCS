package org.kodejava.example.util.logging;

import java.util.logging.Level;

public class LogLevelSeverityCompare {
    public static void main(String[] args) {
        Level info = Level.INFO;
        Level warning = Level.WARNING;
        Level finest = Level.FINEST;

        //
        // To compare the Level severity we compare the intValue of the Level.
        // Each level is assigned a unique integer value as the severity
        // weight of the level.
        //
        if (info.intValue() < warning.intValue()) {
            System.out.println(info + "(" + info.intValue() + ") is less severe than " +
                    warning + "(" + warning.intValue() + ")");
        }

        if (finest.intValue() < info.intValue()) {
            System.out.println(finest + "(" + finest.intValue() + ") is less severe than " +
                    info + "(" + info.intValue() + ")");
        }
    }
}
