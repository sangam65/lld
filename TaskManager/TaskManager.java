package TaskManager;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import TaskManager.entities.*;
import TaskManager.enums.TaskPriority;
import TaskManager.enums.TaskStatus;
import TaskManager.exception.TaskException;
import TaskManager.exception.UserException;


public class TaskManager {
    public static TaskManager taskManager=null;
 
    private CopyOnWriteArrayList<User>users;
    private Map<String,Task>tasks;    
    private TaskManager(){
       
        this.users=new CopyOnWriteArrayList<>();
        this.tasks = new ConcurrentHashMap<String, Task>();
        
        
    }
    public  static TaskManager getInstance(){
        if(taskManager==null){
            synchronized(TaskManager.class){
            taskManager=new TaskManager();}
        }
        return taskManager;
    }
    public List<Task> getTaskList() {
       return tasks.values()
       .stream()
       .toList();

        
    }
    public synchronized void addTask(Task task,User user) {
        if(!users.contains(user)){
            throw new UserException("user not found");
        }
        if(tasks.containsKey(task.getTaskId())){
            throw new TaskException("task is already present ");
        }
        task.setAssignee(user);
        tasks.put(task.getTaskId(),task);

        
        
    } 
    public synchronized void createUser(User user){
        if(users.contains(user)){
            throw new UserException("user is already present");
        }
        users.add(user);
    }
    public synchronized boolean updateTask(Task task,TaskStatus taskStatus) throws TaskException{
        if(!tasks.containsKey(task.getTaskId())){
            throw new TaskException("task not found , please add task ");
        }
        task.updateTaskStatus(taskStatus);
        return true;

    }
    
    public List<Task> getPriorityTasks(){
        
        return tasks.values()
        .stream()
        .filter(task->task.getTaskPriority().equals(TaskPriority.CRITICAL))
        .toList();

    }

    public List<Task> getCompletedTasks(){
        //   will do
        return tasks.values()
        .stream().
        filter(task->task.getTaskStatus().equals(TaskStatus.DONE))
        .toList();
    }
    
    public Task taskById(String taskId) throws TaskException{
        if(!tasks.containsKey(taskId))
        throw new TaskException("task not found , please add task ");
        return tasks.get(taskId);
    }

    public synchronized void reassignTask(Task task,User user) throws TaskException,UserException{
        if(!tasks.containsKey(task.getTaskId()))
        throw new TaskException("task not found , please add task ");
        if(!users.contains(user)){
            throw new UserException("user not found,please add user");
        }

        task.setAssignee(user);
    }
    





   


}
