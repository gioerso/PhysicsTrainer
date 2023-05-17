package com.example.physicstrainer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

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

public class QuestionActivity extends BaseClass implements AdapterView.OnItemClickListener{
    ListView lv;
    LVQAAdapter arad;
    Application App = new Application();
    int item_id;
    TestList testList;
    List<TestList> qs;
    List<Question> questionList;
    TextView tvQs;
    MathView mv;
    private int questionsCount = 0;
    private int questionSize;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionact);

        lv = (ListView) findViewById(R.id.LV_QActivityA);
        tvQs = (TextView) findViewById(R.id.tvQuestion_QActA);


        Application App1 = (Application)getApplicationContext();
        //arad = new LVQAAdapter(App1, App);

        mv = findViewById(R.id.tvText_QActA);

        WebSettings settings = mv.getSettings();
        //settings.setTextZoom(120);
        settings.setDefaultFontSize(25);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            item_id = Integer.valueOf(extras.get("item_id").toString());

            Block block = BlocksHelper.getBlockByID(item_id);
            questionList = block.GetQuestion();
            questionSize = questionList.size();

            tvQs.setText(tvQs.getText() + "1 из " + String.valueOf(questionSize));

            arad = new LVQAAdapter(App1, App);

            mv.setText(questionList.get(0).GetText());
        }
        else
        {
            arad = new LVQAAdapter(App1, App);
            qs = App.getTestList();
            questionSize = qs.size();

            tvQs.setText(tvQs.getText() + "1 из " + String.valueOf(questionSize));
            mv.setText(qs.get(0).GetTitle());
        }

        lv.setAdapter(arad);
        lv.setOnItemClickListener(this);
    }

    public void onFail(View view){
        setResult(FINAL_QUESTION_FAIL);
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if(questionsCount < questionSize - 1){
            questionsCount++;
            mv.setText(questionList.get(questionsCount).GetText());

            tvQs.setText("Вопрос: " + String.valueOf(questionsCount + 1) + " из " + String.valueOf(questionSize));
        }
        else{
            setResult(FINAL_QUESTION);
            Intent intent = getIntent();
            intent.putExtra(String.valueOf("item_id"), item_id);
            finish();
        }
//        if(i == testList.GetTrueAnswer()){
//            setResult(FINAL_QUESTION);
//            Intent intent = getIntent();
//            intent.putExtra(String.valueOf("item_id"), item_id);
//            finish();
//        }
//        else{
//            setResult(FINAL_QUESTION_FAIL);
//            Intent intent = getIntent();
//            intent.putExtra(String.valueOf("item_id"), item_id);
//            finish();
//        }
        arad.notifyDataSetChanged();
        lv.invalidateViews();
    }
}
