package com.example.physicstrainer.serialize;

public class UserBlock {
    public  UserBlock (long id, User user, Block block){
        this.user = user;
        this.block = block;
    }

    private long id;
    private User user;
    private Block block;
}
