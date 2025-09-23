package StackOverFlow.Content;


import StackOverFlow.entities.User;

public interface Comment {
    
    
    public String getCommentId() ;
    public String getMessage() ;
    public void setMessage(String message) ;
    public String getContentId();
    public User getUser() ;
    
    
}
