package StackOverFlow.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import StackOverFlow.Content.Comment;
import StackOverFlow.enums.Tag;

public class Question {
    private final String quetsionId;
    private final User user;
    private List<Answer>answers;
    private List<Comment>comments;
    private List<Tag>tags;
    private Answer acceptedAnswer;
    
    public Question( User user) {
        this.quetsionId = UUID.randomUUID().toString();
        this.user = user;
        this.answers=new ArrayList<>();
        this.tags=new ArrayList<>();
        this.comments=new ArrayList<>();
        this.acceptedAnswer=null;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
    public void addAnswers(Answer answer) {
        this.answers .add(answer);
    }
    public List<Comment> getComments() {
        return comments;
    }
    public void addComments(Comment comment) {
        this.comments .add(comment);
    }
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    public Answer getAcceptedAnswer() {
        return acceptedAnswer;
    }
    public synchronized void setAcceptedAnswer(Answer acceptedAnswer) {
        this.acceptedAnswer = acceptedAnswer;
    }
    public String getQuetsionId() {
        return quetsionId;
    }
    public User getUser() {
        return user;
    }



}
