package LoggingFramework;

import java.util.concurrent.ConcurrentHashMap;

import LoggingFramework.enums.LogLevel;
import LoggingFramework.strategies.appender.ConsoleAppender;

public class LoggerFactory {
    private static final ConcurrentHashMap<String,Logger>loggers=new ConcurrentHashMap<>();
    private static LogLevel logLevel=LogLevel.INFO;

    public static Logger  getLogger(Class<?>clazz){
        return new Logger(clazz.getSimpleName());
    }
    public static Logger getLogger(String className){
        if(loggers.containsKey(className)){
            return loggers.get(className);
        }

        Logger logger=new Logger(className);
        logger.setLogLevel(logLevel);
        logger.addAppender(new ConsoleAppender());
        return logger;
    }
    public static void setGlobalLevel(LogLevel level){
        logLevel=level;
        loggers.values().forEach(logger->logger.setLogLevel(level));
    }
    
}
