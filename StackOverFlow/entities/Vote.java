package StackOverFlow.entities;

import StackOverFlow.enums.VoteType;

public class Vote {
    private  VoteType voteType;
    public VoteType getVoteType() {
        return voteType;
    }
    public void updateVoteType(VoteType voteType) {
        this.voteType = voteType;
    }
    private final User voter;
    public User getVoter() {
        return voter;
    }
    public Vote(VoteType voteType,User voter){
        this.voter=voter;
        this.voteType=voteType;
    }
    
}
