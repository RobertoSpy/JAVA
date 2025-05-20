package com.example;

import java.io.IOException;
import java.util.logging.*;

public class JPQLLogger {
    private static final Logger logger = Logger.getLogger(JPQLLogger.class.getName());

    public static void setupLogger() {
        try {
            LogManager.getLogManager().reset(); 
            logger.setLevel(Level.ALL);

           
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            logger.addHandler(consoleHandler);

            
            FileHandler fileHandler = new FileHandler("application.log", true);
            fileHandler.setLevel(Level.INFO);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error setting up file handler", e);
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void logError(String message, Exception e) {
        logger.log(Level.SEVERE, message, e);
    }
}
