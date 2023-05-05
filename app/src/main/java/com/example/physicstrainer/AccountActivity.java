package com.example.physicstrainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class AccountActivity extends BaseClass{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);
    }

    public void GoToQuestion_AA(View view){
        Intent intent = new Intent(this, MainAction.class);
        startActivity(intent);
        finish();
    }
    public void GoToTheory_AA(View view){
        Intent intent = new Intent(this, TheoryAction.class);
        startActivity(intent);
        finish();
    }
}
