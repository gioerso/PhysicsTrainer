package com.example.physicstrainer.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.physicstrainer.BaseClass;
import com.example.physicstrainer.R;

public class LoginActivity extends BaseClass {
    EditText et_name;
    TextView twName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        SharedPreferences sp = getSharedPreferences("hasVisited", Context.MODE_PRIVATE);
        et_name = (EditText) findViewById(R.id.ETName_logAct);

        if (sp.getBoolean("hasVisited", false) == false) {
            // Сработает если Вход первый
            //Ставим метку что вход уже был
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit(); //После этого hasVisited будет уже true и будет означать, что вход уже был

        }
        else
        {
            Intent intent = new Intent(this, newMainAction.class);
            startActivity(intent);
            finish();
        }
    }

    public void ButtonLogin(View view){
        // закидываем в БД и присваиваем ID
        SharedPreferences sp = getSharedPreferences("user_name", Context.MODE_PRIVATE);

        SharedPreferences.Editor e = sp.edit();
        e.putString("user_name", et_name.getText().toString());
        e.commit();

        // где-то тут должен быть блок с бд, но в API не реализовано

        Intent intent = new Intent(this, newMainAction.class);
        startActivity(intent);
        finish();
    }
}
