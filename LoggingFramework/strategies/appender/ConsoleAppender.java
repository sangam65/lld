package LoggingFramework.strategies.appender;

import LoggingFramework.entities.LogMessage;
import LoggingFramework.strategies.formatter.LogFormatter;
import LoggingFramework.strategies.formatter.SimpleTextFormatter;

public class ConsoleAppender implements LogAppender{
    private LogFormatter logFormatter;
    public ConsoleAppender(){
        this.logFormatter=new SimpleTextFormatter();
    }

    @Override
    public void append(LogMessage logMessage) {
        System.out.println(logFormatter.format(logMessage));
    }

    @Override
    public void close() {
    
    
    }

    @Override
    public void setFormatter(LogFormatter logFormatter) {
        this.logFormatter=logFormatter;
    }

    @Override
    public LogFormatter getLogFormatter() {
        return logFormatter;
    }
 
}
