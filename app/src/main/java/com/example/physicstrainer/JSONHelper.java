package com.example.physicstrainer;

import android.os.StrictMode;
import android.util.Log;

import com.example.physicstrainer.serialize.Question;
import com.google.firebase.annotations.concurrent.Background;
import com.google.gson.Gson;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

// класс - помошник для коннекта к API
public class JSONHelper{



    private interface Questions {
        @GET("/get/questions")
        Call<List<Question>> questions();
    }
    private interface questionByID {
        @GET("/get/questions")
        Call<List<Question>> questions();
    }


    private static final String QUESTIONS_URL = "http://2.59.37.134:8080";

    @Background
    protected static List<Question> importQuestion() {

        Gson gson = new Gson();
        List<Question> questions = null;


        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);

        try {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(QUESTIONS_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

            Questions qs = retrofit.create(Questions.class);
            Call<List<Question>> call = qs.questions();
            questions = call.execute().body();

            Log.i("Request to API", "Questions: FINE! Size = " + questions.size());
        } catch (Exception e) {
            Log.i("API/questions", e.getMessage());
        }

        return questions;
    }
}
