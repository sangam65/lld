package LoggingFramework;

import LoggingFramework.enums.LogLevel;
import LoggingFramework.strategies.appender.FileAppender;

public class Main {
 private static final Logger logger =LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // Add file appender
        logger.addAppender(new FileAppender("app.log"));
        
        // Set log level
        logger.setLogLevel(LogLevel.DEBUG);
        
        // Log messages
        logger.debug("This is a debug message");
        logger.info("Application started");
        logger.warn("This is a warning");
        logger.error("This is an error message");
    }
}
