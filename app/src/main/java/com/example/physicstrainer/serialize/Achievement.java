package com.example.physicstrainer.serialize;

public enum Achievement {
    EXAMPLE(0, 0),
    VERY_EASY(1, 10),
    EASY(2, 25),
    MEDIUM(3, 50),
    HARD(4, 75),
    VERY_HARD(5, 100);

    private final int complexity;
    private final long scoreReward;

    Achievement(int complexity, long scoreReward) {
        this.complexity = complexity;
        this.scoreReward = scoreReward;
    }
}
