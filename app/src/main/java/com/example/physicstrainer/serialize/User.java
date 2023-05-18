package com.example.physicstrainer.serialize;

import java.util.List;

public class User {
    private long id;
    private String name;
    private long score;
    private List<UserBlock> userFinishedBlocks;

    public void increaseScore(long increment) {
        score += increment;
    }
    public void decreaseScore(long decrement) {
        score -= decrement;
    }

    public User(User user){
        this.id = user.id;
        this.name = user.name;
        this.score = user.score;
        this.userFinishedBlocks = user.userFinishedBlocks;
    }

    protected void finalize() throws Throwable{ System.out.println("DS/User: User destroyed");};

    public User(long id, String name, long score, List<UserBlock> userFinishedBlocks){
        this.id = id;
        this.name = name;
        this.score = score;
        this.userFinishedBlocks = userFinishedBlocks;
    }
    public User(long score, String name){
        this.score = score;
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public long getID(){
        return id;
    }
    public long getScore(){
        return score;
    }
    public List<UserBlock> getUserFinishedBlocks(){
        return userFinishedBlocks;
    }
}
