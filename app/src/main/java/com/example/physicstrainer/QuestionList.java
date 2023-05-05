package com.example.physicstrainer;

import java.io.Serializable;

public class QuestionList implements Serializable {
    String Title;
    String Text;
    int ID;

    public QuestionList(int id, String title, String text){
        this.Title = title;
        this.ID = id;
        this.Text = text;
    }

    // Get-теры
    public String GetTitle(){
        return Title;
    }
    public String GetText(){
        return Text;
    }
    public int GetID() {return ID;}
}
