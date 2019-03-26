package controller;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class Logger {
    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(Logger.class.getName());

    static {
        Handler fileHandler;
        try{
            fileHandler = new FileHandler("log%u.%g.txt", 1024 * 1024, 20);
            fileHandler.setFormatter(new SimpleFormatter());
            log.addHandler(fileHandler);
            fileHandler.setLevel(Level.ALL);
            log.setLevel(Level.ALL);
            log.config("Configuration done.");
        } catch (IOException exception){
            log.log(Level.SEVERE, "Error occur in FileHandler.", exception);
        }
    }

    /**
     * Message with level INFO
     * @param message message string
     */
    public static void info(String message) {
        log.log(Level.INFO, message);
    }

    /**
     * Message with level SEVERE
     * @param message message string
     */
    public static void severe(String message) {
        log.log(Level.SEVERE, message);
    }
}