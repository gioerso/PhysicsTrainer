package com.example.physicstrainer.serialize;

import com.example.physicstrainer.JSONHelper;

import java.util.List;

public class Question {

    public Question(Question question){
        this.id = question.id;
        this.block = question.block;
        this.name = question.name;
        this.text = question.text;
        this.image = question.image;
        this.achievement = question.achievement;
    }

    public Question(){

    }
    private long id;
    private Block block;
    private String name;
    private String text;
    private Image image;
    private Achievement achievement;
    private List<ThemeQuestion> questionThemes;

    public long GetID(){
        return id;
    }
    public Block GetBlock(){
        return block;
    }
    public String GetName(){
        return name;
    }
    public String GetText(){
        return text;
    }
    public Image GetImage(){
        return image;
    }
    public Achievement GetAchievement(){
        return achievement;
    }
}
