package LoggingFramework.strategies.formatter;

import LoggingFramework.entities.LogMessage;

public class SimpleTextFormatter implements LogFormatter{

    @Override
    public String format(LogMessage logMessage) {
        return "log "+logMessage.getMessage()+" "+logMessage.getThreadName()+logMessage.getLoggerName()+" "+logMessage.getLocalDateTime();
    }

}
