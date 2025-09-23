package StackOverFlow.Content;

import java.util.UUID;

import StackOverFlow.entities.User;

public class AnswerComment implements Comment {
    private String message;
    private final String commentId;
    private final User user;
    private final String contentId;

    public AnswerComment(String message, User user, String contentId) {
        this.commentId = UUID.randomUUID().toString();
        this.message = message;
        this.user = user;

        this.contentId = contentId;
    }

    @Override
    public String getCommentId() {
        return commentId;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getContentId() {
        return contentId;
    }

    @Override
    public User getUser() {
        return user;
    }
}
