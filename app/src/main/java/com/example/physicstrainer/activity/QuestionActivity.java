package com.example.physicstrainer.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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

public class QuestionActivity extends BaseClass {
    int item_id;
    List<TestList> qs;
    List<Question> questionList;
    TextView tvQs;
    ConstraintLayout numericL, buttonL;
    Button trueAnswer, falseAnswer;
    MathView mv;
    final Handler handler = new Handler();
    int trueAnswersCount = 0;
    int scoreCount = 0;
    private int questionsCount = 0;
    private int questionSize;


    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        tvQs = (TextView) findViewById(R.id.tvQuestion_QActA);
        mv = findViewById(R.id.tvText_QActA);

        WebSettings settings = mv.getSettings();
        settings.setDefaultFontSize(25);

        trueAnswer = findViewById(R.id.answer_1);
        falseAnswer = findViewById(R.id.answer_2);
        trueAnswer.setBackgroundColor(Color.rgb(41, 63, 66));
        falseAnswer.setBackgroundColor(Color.rgb(41, 63, 66));

        numericL = findViewById(R.id.numeric_answer);
        buttonL = findViewById(R.id.boolean_answer);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            item_id = Integer.parseInt(extras.get("item_id").toString());

            Block block = BlocksHelper.getBlockByID(item_id);
            questionList = block.GetQuestion();
            questionSize = questionList.size();

            tvQs.setText(tvQs.getText() + "1 из " + questionSize);

            mv.setText(questionList.get(0).GetText());

            if (Objects.equals(questionList.get(questionsCount).GetAnswerType(), "numeric")) {
                numericL.setVisibility(View.VISIBLE);
                buttonL.setVisibility(View.GONE);
            } else {
                numericL.setVisibility(View.GONE);
                buttonL.setVisibility(View.VISIBLE);
            }
        } else {
            questionSize = qs.size();

            tvQs.setText(tvQs.getText() + "1 из " + String.valueOf(questionSize));
            mv.setText(qs.get(0).GetTitle());
        }
    }

    @SuppressLint("SetTextI18n")
    public void onFail(View view) {
        if (questionsCount < questionSize - 1) {
            if (questionList.get(questionsCount).GetAnswer().equals("Неверно")) {
                trueAnswersCount++;
                scoreCount += questionList.get(questionsCount).GetAchievement().getScoreReward();

                falseAnswer.setBackgroundColor(Color.GREEN);
                trueAnswer.setBackgroundColor(Color.RED);
            } else
            {
                trueAnswer.setBackgroundColor(Color.rgb(0,128,0));
                falseAnswer.setBackgroundColor(Color.RED);
            }

            final Runnable r = new Runnable() {
                public void run() {
                    trueAnswer.setBackgroundColor(Color.rgb(41, 63, 66));
                    falseAnswer.setBackgroundColor(Color.rgb(41, 63, 66));

                    questionsCount++;
                    mv.setText(questionList.get(questionsCount).GetText());
                    tvQs.setText("Вопрос: " + String.valueOf(questionsCount + 1) + " из " + String.valueOf(questionSize));
                }
            };
            handler.postDelayed(r, 1000);

            if (questionList.get(questionsCount).GetAnswerType().equals("0"))
            {
                setNumericVisible();
            }
            else
            {
                setButtonVisible();
            }
        } else
        {
            if (questionList.get(questionsCount).GetAnswer().equals("Верно"))
            {
                trueAnswersCount++;
            }

            Intent data = getIntent();
            data.putExtra("trueAnswers", trueAnswersCount);
            data.putExtra("scoreCount", scoreCount);

            setResult(FINAL_QUESTION,data);
            finish();
        }
    }

    @SuppressLint("SetTextI18n")
    public void onTrue(View view) {
        if (questionsCount < questionSize - 1) {
            if (questionList.get(questionsCount).GetAnswer().equals("Верно"))
            {
                trueAnswer.setBackgroundColor(Color.rgb(0,128,0));
                falseAnswer.setBackgroundColor(Color.RED);
                trueAnswersCount++;
                scoreCount += questionList.get(questionsCount).GetAchievement().getScoreReward();
            }
            else
            {
                falseAnswer.setBackgroundColor(Color.GREEN);
                trueAnswer.setBackgroundColor(Color.RED);
            }

            final Runnable r = new Runnable() {
                public void run() {
                    trueAnswer.setBackgroundColor(Color.rgb(41, 63, 66));
                    falseAnswer.setBackgroundColor(Color.rgb(41, 63, 66));

                    questionsCount++;
                    mv.setText(questionList.get(questionsCount).GetText());
                    tvQs.setText("Вопрос: " + String.valueOf(questionsCount + 1) + " из " + String.valueOf(questionSize));
                }
            };
            handler.postDelayed(r, 1000);

            if (questionList.get(questionsCount).GetAnswerType().equals("0"))
            {
                setNumericVisible();
            }
            else
            {
                setButtonVisible();
            }
        }
        else
        {
            if (questionList.get(questionsCount).GetAnswer().equals("Верно"))
            {
                trueAnswersCount++;
            }
            Intent data = getIntent();
            data.putExtra("trueAnswers", trueAnswersCount);
            data.putExtra("scoreCount", scoreCount);

            setResult(FINAL_QUESTION,data);
            finish();
        }
    }
    private void setNumericVisible(){
        numericL.setVisibility(View.VISIBLE);
        buttonL.setVisibility(View.GONE);
    }
    private void setButtonVisible(){
        numericL.setVisibility(View.GONE);
        buttonL.setVisibility(View.VISIBLE);
    }
}
