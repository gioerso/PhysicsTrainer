package com.example.physicstrainer.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
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

        startActivityForResult(intent, ON_QUESTION);
    }

    public void toMainActivity(View view){
        Intent intent = new Intent(this, newMainAction.class);
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
        if (resultCode == FINAL_QUESTION) {
            Bundle extras = data.getExtras();
            int trueAnswers = Integer.parseInt(extras.get("trueAnswers").toString());
            int scoreCount = Integer.parseInt(extras.get("scoreCount").toString());
            int blockID = Integer.parseInt(extras.get("item_id").toString());

            // item_id = id block
            SharedPreferences sp = getSharedPreferences("user_name", Context.MODE_PRIVATE);

            User user = new User(Integer.parseInt(sp.getString("user_id","0")));
            Block block = new Block(blockID);

            if(trueAnswers != BlockList.get(blockID-1).GetQuestion().size()){
                UsersHelper.blockCompletion(user,block);

                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

                dlgAlert.setMessage("Ты ответил на "+ trueAnswers + " вопросов, но баллов мы тебе не начислили - начисляем только за правильные решения :)");
                dlgAlert.setTitle("Блок решен с ошибками!");
                dlgAlert.setPositiveButton("Печалька:(",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //dismiss the dialog
                            }
                        });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
            else
            {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

                dlgAlert.setMessage("Тебе начисленно " + scoreCount + " баллов. Продолжай в том же духе!");
                dlgAlert.setTitle("Блок завершен!");
                dlgAlert.setPositiveButton("Отлично!",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //dismiss the dialog
                            }
                        });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
        }
        else{
            Toast toast = Toast.makeText(this, "Произошла ошибка.",Toast.LENGTH_LONG);
            toast.show();
        }
    }
}

