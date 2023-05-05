package com.example.physicstrainer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends BaseClass {
    EditText et_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        SharedPreferences sp = getSharedPreferences("hasVisited", Context.MODE_PRIVATE);
        boolean hasVisited = sp.getBoolean("hasVisited", false);
        et_name = (EditText) findViewById(R.id.ETName_logAct);

//        if (!hasVisited) {
//            // Сработает если Вход первый
//            //Ставим метку что вход уже был
//            SharedPreferences.Editor e = sp.edit();
//            e.putBoolean("hasVisited", true);
//            e.commit(); //После этого hasVisited будет уже true и будет означать, что вход уже был
//
//        }
//        else
//        {
//            Intent intent = new Intent(this, MainAction.class);
//            startActivity(intent);
//            finish();
//        }
    }

    public void ButtonLogin(View view){
        // закидываем в БД и присваиваем ID

        Intent intent = new Intent(this, MainAction.class);
        startActivity(intent);
        finish();
    }
}
