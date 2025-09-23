package StackOverFlow.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import StackOverFlow.Content.Comment;



public class Answer {
    private final String answerId;
    private final User author;
    private List<Comment>comments;

    private final Question question;
 
    public Question getQuestion() {
        return question;
    }

    private boolean acceptedStatus;

   
    

    public Answer( User author,Question question) {
        this.answerId = UUID.randomUUID().toString();
        this.author= author;
        this.comments=new ArrayList<>();
        this.acceptedStatus=false;
        this.question=question;
    }
   
    public String getAnswerId() {
        return answerId;
    }
    public User getAuthor() {
        return author;
    }
    public List<Comment> getComments() {
        return comments;
    }

    public synchronized void addComments(Comment comment) {
        this.comments.add(comment);
    }

    public boolean isAcceptedStatus() {
        return acceptedStatus;
    }

    public void setAcceptedStatus(boolean acceptedStatus) {
        this.acceptedStatus = acceptedStatus;
    }

}
