package com.example.physicstrainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class TheoryAction extends BaseClass{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theory_activity);
    }

    public void GoToQuestion(View view){
        Intent intent = new Intent(this, MainAction.class);
        startActivity(intent);
        finish();
    }
    public void GoToAccount(View view){
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
        finish();
    }
}
