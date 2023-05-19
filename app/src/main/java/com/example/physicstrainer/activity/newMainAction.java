package com.example.physicstrainer.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.physicstrainer.BaseClass;
import com.example.physicstrainer.R;
import com.example.physicstrainer.helpers.UsersHelper;
import com.example.physicstrainer.serialize.Block;

import java.util.List;

public class newMainAction extends BaseClass {

    TextView nameTextView;
    //Spinner sp;
    String[] chapters = { "Механика", "МКТ и ТД", "Электростатика", "Электрический ток", "Магнитные поля","Оптика", "Квантовая и ядерная физика"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_action);

        SharedPreferences sharedPreferences = getSharedPreferences("user_name", Context.MODE_PRIVATE);
        nameTextView = (TextView) findViewById(R.id.tvText_QAct);
        nameTextView.setText(nameTextView.getText().toString().replace("name",sharedPreferences.getString("user_name", "name")));

        Spinner sp = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, chapters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        sp.setOnItemSelectedListener(itemSelectedListener);
    }

    public void nextEducation(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("user_name", Context.MODE_PRIVATE);

        List<Block> blockList = UsersHelper.getUnfinishedBlocks(Integer.parseInt(sharedPreferences.getString("user_id","1")));

        Intent intent = new Intent(this, new MainAction(blockList).getClass());
        startActivity(intent);
        finish();
    }

    public void selectChapter(View view){
        Intent intent = new Intent(this, MainAction.class);
        startActivity(intent);
        finish();
    }
}
