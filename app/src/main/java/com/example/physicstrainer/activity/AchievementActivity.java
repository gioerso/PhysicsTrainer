package com.example.physicstrainer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.physicstrainer.Application;
import com.example.physicstrainer.BaseClass;
import com.example.physicstrainer.R;
import com.example.physicstrainer.lv_adapters.AchievementAdapter;

public class AchievementActivity extends BaseClass {

    AchievementAdapter achievementAdapter;
    ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement_act);

        lv = (ListView) findViewById(R.id.LV_AchievementAct);

        Application App = (Application)getApplicationContext();
        achievementAdapter = new AchievementAdapter(App);
        lv.setAdapter(achievementAdapter);
    }
    public void ReturnToAccountButton(View view){
        finish();
    }
}
