package TaskManager.entities;

import java.util.Date;
import java.util.UUID;

public class Comment {
    private final String commentId;
    private final String message;
    private final User author;
    private final Date timestamp;
    private final String taskId;


   


    public String getTaskId() {
        return taskId;
    }
    public Comment(String message,User author,String taskId){
        this.author=author;
        this.timestamp=new Date();
        this.commentId=UUID.randomUUID().toString();
        this.message=message;
        this.taskId=taskId;
    }
     public String getCommentId() {
        return commentId;
    }


    public String getMessage() {
        return message;
    }


    public User getAuthor() {
        return author;
    }


    public Date getTimestamp() {
        return timestamp;
    }

}
