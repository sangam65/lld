package TaskManager.enums;

public enum TaskStatus {
    NOT_STARTED("NOT_STARTED",1),
    IN_PROGRESS("IN_PROGRESS",2),
    DONE("DONE",3);

    private final String taskStatus;
    private final int value;

    private TaskStatus(String taskStatus,int value){
        this.taskStatus=taskStatus;
        this.value=value;
    }

    public int getValue(){
        return value;
    }
    public String getTaskStatus(){
        return taskStatus;
    }
}
