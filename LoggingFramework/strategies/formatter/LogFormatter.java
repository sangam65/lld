package LoggingFramework.strategies.formatter;

import LoggingFramework.entities.LogMessage;

public interface LogFormatter {
    String format(LogMessage logMessage);
}
