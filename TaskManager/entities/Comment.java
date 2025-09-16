package TaskManager.entities;

import java.util.Date;
import java.util.UUID;

public class Comment {
    private final String commentId;
    private final String message;
    private final User author;
    private final Date timestamp;


   


    public Comment(String message,User author){
        this.author=author;
        this.timestamp=new Date();
        this.commentId=UUID.randomUUID().toString();
        this.message=message;
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
