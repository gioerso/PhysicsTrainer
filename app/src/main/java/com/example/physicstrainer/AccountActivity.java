package com.example.physicstrainer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.physicstrainer.helpers.UsersHelper;
import com.example.physicstrainer.serialize.User;

import java.util.List;

public class AccountActivity extends BaseClass{
    TextView tvHello;
    ListView lv_PersonalStat, lv_UserStat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

        tvHello = (TextView) findViewById(R.id.tvHello_AA);

        SharedPreferences sp = getSharedPreferences("user_name", Context.MODE_PRIVATE);
        String name = tvHello.getText().toString();
        name = name.replace("username", sp.getString("user_name", "name"));
        tvHello.setText(name);


        // временный костыль
        lv_UserStat = (ListView) findViewById(R.id.LVStat_AA);
        ArrayAdapter<String> personalStatAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        personalStatAdapter.add("Вы набрали очков за всю историю:" + UsersHelper.getUser(1).getScore());
        lv_UserStat.setAdapter(personalStatAdapter);

        lv_PersonalStat = (ListView) findViewById(R.id.LVStatScr_AA);
        ArrayAdapter<String> usersStatAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        List<User> userList = UsersHelper.getUsers();
        for(int i = 0; i < userList.size() - 1; i++){
            usersStatAdapter.add(userList.get(i).getName() + " набрал " + userList.get(i).getScore());
        }
        lv_PersonalStat.setAdapter(usersStatAdapter);
    }

    public void GoToQuestion_AA(View view){
        Intent intent = new Intent(this, MainAction.class);
        startActivity(intent);
        finish();
    }
    public void GoToAchievement(View view){
        Intent intent = new Intent(this, AchievementActivity.class);
        startActivity(intent);
        finish();
    }
    public void GoToTheory_AA(View view){
        Intent intent = new Intent(this, TheoryAction.class);
        startActivity(intent);
        finish();
    }
}
