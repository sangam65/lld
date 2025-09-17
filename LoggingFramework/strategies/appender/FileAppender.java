package LoggingFramework.strategies.appender;

import java.io.FileWriter;
import java.io.IOException;

import LoggingFramework.entities.LogMessage;
import LoggingFramework.strategies.formatter.LogFormatter;
import LoggingFramework.strategies.formatter.SimpleTextFormatter;

public class FileAppender  implements LogAppender{
    private LogFormatter logFormatter;
    private FileWriter fileWriter;
    public FileAppender(String filePath){
        this.logFormatter=new SimpleTextFormatter();
        try{
            this.fileWriter=new FileWriter(filePath, true);
        }
        catch(IOException e){
            System.out.println("failed to create file for logs, exception"+e.getMessage());
        }
    }

    @Override
    public synchronized void append(LogMessage logMessage) {
        try{
            fileWriter.write(logFormatter.format(logMessage)+"\n");
            fileWriter.flush();
        }
        catch(IOException e){
             System.out.println("Failed to write logs to file, exception: " + e.getMessage());
        }
    }

    @Override
    public  void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to close logs file, exception: " + e.getMessage());
        }
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
