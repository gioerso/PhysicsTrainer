package com.example.physicstrainer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.physicstrainer.Application;
import com.example.physicstrainer.BaseClass;
import com.example.physicstrainer.R;
import com.example.physicstrainer.TestList;
import com.example.physicstrainer.lv_adapters.LVQAAdapter;

import java.util.List;

public class QuestionActivity extends BaseClass implements AdapterView.OnItemClickListener{
    ListView lv;
    LVQAAdapter arad;
    Application App = new Application();
    int item_id;
    TestList testList;
    List<TestList> qs;
    TextView tw, tvQs;
    private int questionsCount = 0;
    private int questionSize;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionact);

        lv = (ListView) findViewById(R.id.LV_QActivityA);
        tvQs = (TextView) findViewById(R.id.tvQuestion_QActA);

        lv.setOnItemClickListener(this);

        Application App1 = (Application)getApplicationContext();
        arad = new LVQAAdapter(App1, App);
        questionSize = App1.getTestListSize();
        tvQs.setText(tvQs.getText() + "1 из " + String.valueOf(questionSize));
        tw = (TextView) findViewById(R.id.tvText_QActA);

        lv.setAdapter(arad);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            item_id = Integer.valueOf(extras.getString(String.valueOf("item_id")));
            //qs = (List<TestList>) extras.getSerializable(String.valueOf("item_data"));
            qs = App.getTestList();
            testList = new TestList(qs.get(0));
            tw.setText(new TestList(qs.get(0)).GetTitle());
        }
    }

    public void onFail(View view){
        setResult(FINAL_QUESTION_FAIL);
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(questionsCount < App.getTestListSize() - 1){
            questionsCount++;
            tw.setText(new TestList(qs.get(questionsCount)).GetTitle());
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
