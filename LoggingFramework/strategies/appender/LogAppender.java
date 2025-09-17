package LoggingFramework.strategies.appender;

import LoggingFramework.entities.LogMessage;
import LoggingFramework.strategies.formatter.LogFormatter;

public interface LogAppender {
    void append(LogMessage logMessage);
    void close();
    void setFormatter(LogFormatter logFormatter);
    LogFormatter getLogFormatter();
}
