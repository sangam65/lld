package TaskManager.entities;

import TaskManager.enums.TaskPriority;
import TaskManager.enums.TaskStatus;
import TaskManager.exception.TaskException;

import java.util.UUID;
public class Task {
    private final String taskId;
    private final String taskName;
    private TaskStatus taskStatus;
    private TaskPriority taskPriority;
    private User assignee;
    
    public Task( String taskName, TaskPriority taskPriority) {
        this.taskId = UUID.randomUUID().toString();;
        this.taskName = taskName;
        this.taskStatus = TaskStatus.NOT_STARTED;
        this.taskPriority = taskPriority;
    }
    public String getTaskId() {
        return taskId;
    }
    public String getTaskName() {
        return taskName;
    }
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }
    public void updateTaskStatus(TaskStatus taskStatus) {
        if(this.taskStatus.getValue()>taskStatus.getValue()){
            throw new TaskException("Task status can't go backwards");
        }   
        this.taskStatus = taskStatus;
    }
    public TaskPriority getTaskPriority() {
        return taskPriority;
    }
    public void updateTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }
    public User getassignee() {
        return assignee;
    }
    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

   


}   
