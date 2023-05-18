package com.example.physicstrainer.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.physicstrainer.Application;
import com.example.physicstrainer.BaseClass;
import com.example.physicstrainer.QuestionList;
import com.example.physicstrainer.R;
import com.example.physicstrainer.helpers.BlocksHelper;
import com.example.physicstrainer.helpers.UsersHelper;
import com.example.physicstrainer.lv_adapters.LVQAdapter;
import com.example.physicstrainer.serialize.Block;
import com.example.physicstrainer.serialize.User;

import java.util.List;

public class MainAction extends BaseClass implements AdapterView.OnItemClickListener {

    public MainAction() {

    }

    public MainAction(List<Block> blockList) {
        this.BlockList = blockList;
    }

    TextView nameTextView;
    LVQAdapter arQad;
    ListView LVQ;
    protected Application App = new Application();
    private List<Block> BlockList;
    private List<Block> BList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        BlockList = BlocksHelper.getAllBlocks();
        User user = new User(0,"Petya");
        UsersHelper.newUser(user);
        nameTextView = (TextView) findViewById(R.id.tvText_QAct);

        LVQ = (ListView) findViewById(R.id.LV_QActivity);
        Application App1 = (Application)getApplicationContext();
        if(BlockList == null){
            arQad = new LVQAdapter(App1, App);
        }
        else
        {
            arQad = new LVQAdapter(App1, BlockList);
        }

        LVQ.setAdapter(arQad);
        LVQ.setOnItemClickListener(this);
        LVQ.deferNotifyDataSetChanged();

        SharedPreferences sp = getSharedPreferences("user_name", Context.MODE_PRIVATE);
        String name = nameTextView.getText().toString();
        name = name.replace("name", sp.getString("user_name", "name"));
        nameTextView.setText(name);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, QuestionActivity.class);

        intent.putExtra(String.valueOf("item_id"), BlockList.get(i).GetID());

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
//            String before = App.getFullList().get(id).GetText();
//            switch (requestC) {
//                case FINAL_QUESTION:
//                    App.SetQuestion(id, "Выполнено: " + before);
//                    break;
//                case EXIT_QUESTION:
//                    App.SetQuestion(id, "Провалено: " + before);
//                    break;
//                case FINAL_QUESTION_FAIL:
//                    App.SetQuestion(id, "Провалено: " + before);
//                    break;
//            }
        }
        else{
            Toast toast = Toast.makeText(this, "Молодец, ты закончил блок!\n Тебе начисленно 100 баллов!",Toast.LENGTH_LONG);
            toast.show();
        }
    }
}

