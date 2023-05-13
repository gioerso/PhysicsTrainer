package com.example.physicstrainer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.physicstrainer.BaseClass;
import com.example.physicstrainer.R;
import com.example.physicstrainer.helpers.TheoryHelper;
import com.example.physicstrainer.serialize.Theory;

import java.util.List;

public class TheoryActivity extends BaseClass {

    TextView tvTitle, tvText;
    int item_id;
    List<Theory> theoryList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theory_act);

        tvTitle = (TextView) findViewById(R.id.tvTitle_TAct);
        tvText = (TextView) findViewById(R.id.tvText_TAct);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            item_id = Integer.valueOf(extras.getString(String.valueOf("item_id")));
            theoryList = TheoryHelper.getTheoryLessons();
            tvTitle.setText(theoryList.get(0).getName());
            tvText.setText(theoryList.get(0).getText());
        }
    }

    public void BackToTheory(View view){
        setResult(FINAL_THEORY);
        finish();
    }
}
