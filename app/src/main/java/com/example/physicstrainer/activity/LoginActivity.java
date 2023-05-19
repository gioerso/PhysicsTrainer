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
import com.example.physicstrainer.helpers.UsersHelper;
import com.example.physicstrainer.serialize.User;

import java.util.List;

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

        // где-то тут должен быть блок с бд, но в API не реализовано
        User user = new User(0,et_name.getText().toString(),0,null);
        UsersHelper.newUser(user);
        List<User> userList = UsersHelper.getUsers();
        e.putString("user_id", String.valueOf(userList.size()+1));
        e.putString("user_name", et_name.getText().toString());

        e.commit();


        Intent intent = new Intent(this, newMainAction.class);
        startActivity(intent);
        finish();
    }
}
