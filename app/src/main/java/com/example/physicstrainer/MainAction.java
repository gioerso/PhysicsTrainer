package com.example.physicstrainer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.physicstrainer.serialize.Block;
import com.example.physicstrainer.serialize.Question;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainAction extends BaseClass implements AdapterView.OnItemClickListener {
    TextView test;
    LVQAdapter arQad;
    ListView LVQ;
    protected Application App = new Application();
    private List<QuestionList> Qlist;
    private List<Block> BList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Qlist = App.getFullList();
        //BList = App.getBlocksList();

        test = (TextView) findViewById(R.id.tvText_QAct);

        LVQ = (ListView) findViewById(R.id.LV_QActivity);
        Application App1 = (Application)getApplicationContext();
        arQad = new LVQAdapter(App1, App);
        LVQ.setAdapter(arQad);
        LVQ.setOnItemClickListener(this);
        LVQ.deferNotifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, QuestionActivity.class);

        intent.putExtra(String.valueOf("item_id"), String.valueOf(adapterView.getItemIdAtPosition(i)));

//        for(int j = 0; i > App.getTestListSize() - 1; j++){
//            intent.putExtra(String.valueOf("item_data"), App.getTestList().get(j));
//        }

        startActivityForResult(intent, ON_QUESTION);
    }

    public void GoToTheory(View view){
        Intent intent = new Intent(this, TheoryAction.class);
        startActivity(intent);
        finish();
    }

    public void GoToAccount(View view){
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
        finish();
    }

    public void onActivityResult(int requestC, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestC, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            int id = Integer.valueOf(extras.getString(String.valueOf("item_id")));
            String before = App.getFullList().get(id).GetText();
            switch (requestC) {
                case FINAL_QUESTION:
                    App.SetQuestion(id, "Выполнено: " + before);
                    break;
                case EXIT_QUESTION:
                    App.SetQuestion(id, "Провалено: " + before);
                    break;
                case FINAL_QUESTION_FAIL:
                    App.SetQuestion(id, "Провалено: " + before);
                    break;
            }
        }
        else{
            // !!
        }
    }
}

