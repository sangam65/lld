package LoggingFramework.entities;

import java.time.LocalDateTime;

import LoggingFramework.enums.LogLevel;

public class LogMessage {
    private final String message;
    private final LogLevel logLevel;
    private final LocalDateTime localDateTime;
    private final String loggerName;
    private final String threadName;
    public LogMessage(String message, LogLevel logLevel, String loggerName) {
        this.message = message;
        this.logLevel = logLevel;
        this.localDateTime =LocalDateTime.now();
        this.loggerName = loggerName;
        this.threadName = Thread.currentThread().getName();
    }
    public String getMessage() {
        return message;
    }
    public LogLevel getLogLevel() {
        return logLevel;
    }
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
    public String getLoggerName() {
        return loggerName;
    }
    public String getThreadName() {
        return threadName;
    }


}
