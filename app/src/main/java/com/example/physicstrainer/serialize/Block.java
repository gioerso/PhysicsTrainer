package com.example.physicstrainer.serialize;

import java.util.List;

public class Block {
    private long id;
    private String name;
    private List<Question> questions;
    private List<UserBlock> blockFinishedUsers;

    public Block(){

    }
    public Block(long id, String name, List<Question> qs){
        this.id = id;
        this.name = name;
        this.questions = qs;
        //this.blockFinishedUsers = block.blockFinishedUsers;
    }
    public Block(long id){
        this.id = id;
    }

    public Block(Block bl) {
        id = bl.id;
        name = bl.name;
        questions = bl.questions;
    }

    public long GetID(){
        return id;
    }
    public String GetName(){
        return name;
    }
    public List<Question> GetQuestion(){
        return questions;
    }
}
