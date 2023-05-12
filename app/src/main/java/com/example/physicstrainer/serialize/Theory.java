package com.example.physicstrainer.serialize;

import java.util.List;

public class Theory {
    private long id;
    private String name;
    private String text;
    private List<TheoryImage> theoryImages;
    //private Theme theme;

    public Theory(){

    }
    public Theory(Theory theory){
        this.id = theory.id;
        this.name = theory.name;
        this.text = theory.text;
        this.theoryImages = theory.theoryImages;
        //this.theme = theory.theme;
    }
    public Theory(long id, String name, String text, List<TheoryImage> theoryImages){
        this.id = id;
        this.name = name;
        this.text = text;
        this.theoryImages = theoryImages;
        //this.theme = theme;
    }

    public long getID(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getText(){
        return text;
    }
    public List<TheoryImage> getTheoryImages(){
        return theoryImages;
    }
//    //public Theme getThem(){
//        return theme;
//    }
}
