package com.example.physicstrainer.serialize;

import java.util.List;

public class User {
    private long id;
    private long score;
    private List<UserBlock> userFinishedBlocks;

    public void increaseScore(long increment) {
        score += increment;
    }
    public void decreaseScore(long decrement) {
        score -= decrement;
    }
}
