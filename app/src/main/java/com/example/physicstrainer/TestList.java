package com.example.physicstrainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestList implements Serializable {
    int ID;
    int IDTheme;
    int IDQuestion;
    int TrueAnswer;
    String Title;
    List<String> Answers = new ArrayList<>();

    public TestList(int id, int idTheme, int idQuestion, int trueAnswer, String title, List<String> answers){
        ID = id;
        IDTheme = idTheme;
        IDQuestion = idQuestion;
        Title = title;
        TrueAnswer = trueAnswer;
        Answers = answers;
    }

    public TestList(TestList testList) {
        ID = testList.ID;
        IDTheme = testList.IDTheme;
        IDQuestion = testList.IDQuestion;
        Title = testList.Title;
        TrueAnswer = testList.TrueAnswer;
        Answers = testList.Answers;
    }

    // Get-еры
    public int GetID() {
        return ID;
    }
    public int GetIDTheme() {
        return IDTheme;
    }
    public int GetIDQuestion() {
        return IDQuestion;
    }
    public String GetTitle(){
        return Title;
    }
    public List<String> GetListAnswers(){
        return Answers;
    }
    public int GetTrueAnswer() {return TrueAnswer;}

}
