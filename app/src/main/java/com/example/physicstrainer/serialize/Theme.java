package com.example.physicstrainer.serialize;

import java.util.List;

public class Theme {
    private long id;
    private String name;
    private List<Theory> theory;
    private List<ThemeQuestion> themeQuestions;

    public Theme(){

    }
    public Theme(Theme theme){
        this.id = theme.id;
        this.name = theme.name;
        this.theory = theme.theory;
        this.themeQuestions = theme.themeQuestions;
    }
    public Theme(long id, String name, List<Theory> theory, List<ThemeQuestion> tQuestions){
        this.id = id;
        this.name = name;
        this.theory = theory;
        this.themeQuestions = tQuestions;
    }
    public long getID(){
        return id;
    }
    public String getName(){
        return name;
    }
    public List<Theory> getTheory(){
        return theory;
    }
    public List<ThemeQuestion> getThemeQuestions(){
        return themeQuestions;
    }
}
