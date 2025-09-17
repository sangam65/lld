package LoggingFramework;

import java.util.ArrayList;
import java.util.List;

import LoggingFramework.entities.LogMessage;
import LoggingFramework.enums.LogLevel;
import LoggingFramework.strategies.appender.LogAppender;

public class Logger {
    private LogLevel logLevel;
    private final List<LogAppender> appenders;
    private final String className;


    public Logger(String className){
        this.className=className;
        this.logLevel=LogLevel.INFO;
        this.appenders=new ArrayList<>();
    }
    public void setLogLevel(LogLevel logLevel){
        this.logLevel=logLevel;
    } 
  
    public void addAppender(LogAppender logAppender){
        appenders.add(logAppender);
    }

    public void debug(String message){
        log(LogLevel.DEBUG, message);

    }
    public void info(String message){

        log(LogLevel.INFO, message);
    }
    public void error(String message){

        log(LogLevel.ERROR, message);
    }
    public void warn(String message){
        log(LogLevel.WARN,message);

    }
    private void log(LogLevel level,String message){
        if(this.logLevel.getLevel()>=level.getLevel()){
            for(LogAppender appender:appenders){
                LogMessage logMessage=new LogMessage(message, level,className);
                appender.append(logMessage);
            }
        }
    }




}
