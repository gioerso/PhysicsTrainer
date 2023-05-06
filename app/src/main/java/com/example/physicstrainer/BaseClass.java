package com.example.physicstrainer;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class BaseClass extends AppCompatActivity {
    protected static final int ON_QUESTION = 0x000312;
    protected static final int FINAL_QUESTION = 0x000313;
    protected static final int FINAL_QUESTION_FAIL = 0x000333;
    protected static final int EXIT_QUESTION = 0x000314;
    protected static final String EXTRA_TEXT = "text";
    protected static final String EXTRA_ID = "id";

    @Override
    protected void onResume() {
        super.onResume();
    }
    protected void finalize() throws Throwable { Log.i("Application","Application stopped.");}
}
