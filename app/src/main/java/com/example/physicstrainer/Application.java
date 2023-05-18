package com.example.physicstrainer;

import com.example.physicstrainer.helpers.BlocksHelper;
import com.example.physicstrainer.helpers.QuestionsHelper;
import com.example.physicstrainer.serialize.Block;
import com.example.physicstrainer.serialize.Question;

import java.util.ArrayList;
import java.util.List;

public class Application extends android.app.Application{
    String G_STR1;
    String G_STR2;
    private final List<QuestionList> QuestionList = new ArrayList<>();
    private final List<TestList> TestList = new ArrayList<>();
    //private List<Question> qList = QuestionsHelper.getAllQuestions();
    //private List<Block> blocksList = BlocksHelper.getAllBlocks();

    private final List<String> testAnswer = new ArrayList<>();

    public Application(){
        super();
        //qList = QuestionsHelper.getAllQuestions();
        //blocksList = BlocksHelper.getAllBlocks();

        testAnswer.add("Верно");
        testAnswer.add("Неверно");

        //blocksList.add(new Block(1, "Силы в механике", qList));
//        QuestionList.add(new QuestionList(0, "", new Question(qList.get(0)).GetName()));

//        for(int i = 0; i < qList.size(); i++){
//            TestList.add(new TestList(i, 1, i, 1,new Question(qList.get(i)).GetText(), testAnswer));
//        }
    }


    public List<QuestionList> getFullList() {
        return QuestionList;
    }
//    public List<Block> getBlocksList(){
//        return blocksList;
//    }
    public int getQuestionSize() {return QuestionList.size(); }
    public List<TestList> getTestList(){
        return TestList;
    }
    public int getTestListSize(){
        return TestList.size();
    }
    public List<String> getAnswers(int position){
        return TestList.get(position).GetListAnswers();
    }
    public void SetQuestion(int i, String text){
        testAnswer.set(i, text);
    }
}
