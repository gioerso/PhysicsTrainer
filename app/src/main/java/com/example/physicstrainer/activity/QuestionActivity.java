package com.example.physicstrainer.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.physicstrainer.Application;
import com.example.physicstrainer.BaseClass;
import com.example.physicstrainer.R;
import com.example.physicstrainer.TestList;
import com.example.physicstrainer.helpers.BlocksHelper;
import com.example.physicstrainer.lv_adapters.LVQAAdapter;
import com.example.physicstrainer.MathView;
import com.example.physicstrainer.serialize.Block;
import com.example.physicstrainer.serialize.Question;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class QuestionActivity extends BaseClass{
    ListView lv;
    LVQAAdapter arad;
    int item_id;
    TestList testList;
    List<TestList> qs;
    List<Question> questionList;
    TextView tvQs;
    ConstraintLayout numericL, buttonL;
    Button trueAnswer, falseAnswer;
    MathView mv;
    int trueAnswersCount = 0;
    private int questionsCount = 0;
    private int questionSize;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionact);
        tvQs = (TextView) findViewById(R.id.tvQuestion_QActA);
        mv = findViewById(R.id.tvText_QActA);

        WebSettings settings = mv.getSettings();
        settings.setDefaultFontSize(25);
        trueAnswer = findViewById(R.id.answer_1);
        falseAnswer = findViewById(R.id.answer_2);
        numericL = findViewById(R.id.numeric_answer);
        buttonL = findViewById(R.id.boolean_answer);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            item_id = Integer.valueOf(extras.get("item_id").toString());

            Block block = BlocksHelper.getBlockByID(item_id);
            questionList = block.GetQuestion();
            questionSize = questionList.size();

            tvQs.setText(tvQs.getText() + "1 из " + String.valueOf(questionSize));

            mv.setText(questionList.get(0).GetText());

            if(questionList.get(questionsCount).GetAnswerType() == "numeric"){
                numericL.setVisibility(View.VISIBLE);
                buttonL.setVisibility(View.GONE);
            }
            else{
                numericL.setVisibility(View.GONE);
                buttonL.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            questionSize = qs.size();

            tvQs.setText(tvQs.getText() + "1 из " + String.valueOf(questionSize));
            mv.setText(qs.get(0).GetTitle());
        }
    }

    public void onFail(View view){
        if(questionsCount < questionSize - 1){
            if(questionList.get(questionsCount - 1).GetAnswer() == "Неверно"){
                trueAnswersCount++;

                falseAnswer.setBackgroundColor(Color.GREEN);
                trueAnswer.setBackgroundColor(Color.RED);

                SystemClock.sleep(TimeUnit.SECONDS.toMillis(2));

                trueAnswer.setBackgroundColor(Color.BLUE);
                falseAnswer.setBackgroundColor(Color.BLUE);
            }
            else{
                trueAnswer.setBackgroundColor(Color.GREEN);
                falseAnswer.setBackgroundColor(Color.RED);

                SystemClock.sleep(TimeUnit.SECONDS.toMillis(2));

                trueAnswer.setBackgroundColor(Color.BLUE);
                falseAnswer.setBackgroundColor(Color.BLUE);
            }

            questionsCount++;
            mv.setText(questionList.get(questionsCount).GetText());
            tvQs.setText("Вопрос: " + String.valueOf(questionsCount + 1) + " из " + String.valueOf(questionSize));

            if(questionList.get(questionsCount).GetAnswerType() == "numeric"){
                numericL.setVisibility(View.VISIBLE);
                buttonL.setVisibility(View.GONE);
            }
            else{
                numericL.setVisibility(View.GONE);
                buttonL.setVisibility(View.VISIBLE);
            }
        }
        else{
            setResult(FINAL_QUESTION);
            Intent intent = getIntent();
            intent.putExtra("trueAnswers", trueAnswersCount);
            finish();
        }
    }
    public void onTrue(View view){
        if(questionsCount < questionSize - 1){
            if(questionList.get(questionsCount).GetAnswer() == "Верно"){
                trueAnswer.setBackgroundColor(Color.GREEN);
                falseAnswer.setBackgroundColor(Color.RED);
                trueAnswersCount++;
                SystemClock.sleep(TimeUnit.SECONDS.toMillis(1));
            }
            else{
                falseAnswer.setBackgroundColor(Color.GREEN);
                trueAnswer.setBackgroundColor(Color.RED);
                SystemClock.sleep(TimeUnit.SECONDS.toMillis(1));
            }

            questionsCount++;
            mv.setText(questionList.get(questionsCount).GetText());
            tvQs.setText("Вопрос: " + String.valueOf(questionsCount + 1) + " из " + String.valueOf(questionSize));
        }
        else{
            setResult(FINAL_QUESTION);
            Intent intent = getIntent();
            intent.putExtra("trueAnswers", trueAnswersCount);
            finish();
        }
    }



//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//        if(questionsCount < questionSize - 1){
//            questionsCount++;
//            mv.setText(questionList.get(questionsCount).GetText());
//            tvQs.setText("Вопрос: " + String.valueOf(questionsCount + 1) + " из " + String.valueOf(questionSize));
//        }
//        else{
//            setResult(FINAL_QUESTION);
//            Intent intent = getIntent();
//            intent.putExtra(String.valueOf("item_id"), item_id);
//            finish();
//        }
////        if(i == testList.GetTrueAnswer()){
////            setResult(FINAL_QUESTION);
////            Intent intent = getIntent();
////            intent.putExtra(String.valueOf("item_id"), item_id);
////            finish();
////        }
////        else{
////            setResult(FINAL_QUESTION_FAIL);
////            Intent intent = getIntent();
////            intent.putExtra(String.valueOf("item_id"), item_id);
////            finish();
////        }
//        arad.notifyDataSetChanged();
//        lv.invalidateViews();
//    }
}
