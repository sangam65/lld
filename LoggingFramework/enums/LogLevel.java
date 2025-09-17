package LoggingFramework.enums;

public enum LogLevel {
    
    
    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4);

    private final int level;
    public int getLevel() {
        return level;
    }
    private LogLevel(int level){
        this.level=level;
    }

}
