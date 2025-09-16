package TaskManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import TaskManager.entities.*;
import TaskManager.enums.TaskPriority;
import TaskManager.enums.TaskStatus;
import TaskManager.exception.TaskNotFoundException;
import TaskManager.exception.UserNotFoundException;


public class TaskManager {
    public static TaskManager taskManager=null;
    private List<Task>taskList;
    private List<User>users;
    private HashMap<String,List<Task>>userTaskList;
    

    

    
    private TaskManager(){
        this.taskList=new ArrayList<>();
        this.users=new ArrayList<>();
        
    }
    public synchronized static TaskManager getInstance(){
        if(taskManager==null){
            taskManager=new TaskManager();
        }
        return taskManager;
    }
    public List<Task> getTaskList() {
        return taskList;
    }
    public synchronized void addTask(Task task,User user) {
        if(!users.contains(user)){
            throw new UserNotFoundException("user not found");
        }

        task.setassignee(user);
        taskList.add(task);
    } 
    public synchronized void createUser(User user){
        users.add(user);
    }
    public synchronized boolean updateTask(Task task,TaskStatus taskStatus) throws TaskNotFoundException{
        if(!taskList.contains(task)){
            throw new TaskNotFoundException("task not found , please add task ");
        }
        task.updateTaskStatus(taskStatus);
        return true;

    }
    
    public List<Task> getPriorityTasks(){
        
        List<Task> priorityTasks=new ArrayList<>();
        for(Task task:taskList){
            if(task.getTaskStatus().equals(TaskPriority.CRITICAL.toString())){
                priorityTasks.add(task);
            }
        }  
        return priorityTasks;

    }

    public List<Task> getCompletedTasks(){
        //   will do
        List<Task> completedTasks=new ArrayList<>();
        for(Task task:taskList){
            if(task.getTaskStatus().equals(TaskStatus.DONE)){
                completedTasks.add(task);
            }
        }   

        return completedTasks;
    }
    public List<Task> getAlTasks(){
        return taskList;
    }
    public Task taskById(String taskId) throws TaskNotFoundException{
        for(Task task:taskList){
            if(task.getTaskId().equals(taskId)){
                return task;
            }
        } 
        throw new TaskNotFoundException("task not found , please add task ");
    }

    public void reassignTask(Task task,User user) throws TaskNotFoundException{
        if(taskList.contains(task))
        throw new TaskNotFoundException("task not found , please add task ");
    }





   


}
