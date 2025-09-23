package StackOverFlow.entities;

import java.util.UUID;

public class User {
    private final String userId;

    private int reputation;
    public User() {
        this.userId = UUID.randomUUID().toString();
        this.reputation=0;
    }

    public int getReputation() {
        return reputation;
    }
    public void setReputation(int incrRep) {
        this.reputation += incrRep;
    }
     public String getUserId() {
        return userId;
    }
}
