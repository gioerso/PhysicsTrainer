package com.example.physicstrainer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.physicstrainer.Application;
import com.example.physicstrainer.BaseClass;
import com.example.physicstrainer.R;
import com.example.physicstrainer.lv_adapters.TheoryAdapter;

public class TheoryAction extends BaseClass implements AdapterView.OnItemClickListener {
    ListView lv;
    TheoryAdapter tAdapt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theory_activity);

        lv = (ListView) findViewById(R.id.LV_TActivity);
        Application App1 = (Application)getApplicationContext();
        tAdapt = new TheoryAdapter(App1);


        lv.setAdapter(tAdapt);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, TheoryActivity.class);

        intent.putExtra(String.valueOf("item_id"), String.valueOf(adapterView.getItemIdAtPosition(i)));

        startActivityForResult(intent, ON_THEORY);
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
