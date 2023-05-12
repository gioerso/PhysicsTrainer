package com.example.physicstrainer.helpers;

import android.os.StrictMode;
import android.util.Log;

import com.example.physicstrainer.serialize.Question;
import com.example.physicstrainer.serialize.Theory;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class TheoryHelper {

    private static final String QUESTIONS_URL = "http://2.59.37.134:8080";
    private static final StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    private interface IAllTheory {
        @GET("/get/theories")
        Call<List<Theory>> lessons();
    }
    private interface ITheoryByID {
        @GET("/get/theory")
        Call<List<Theory>> lesson(@Query("=") int id);
    }
    private interface ITheoryByTheme {
        @GET("/get/theory/theme")
        Call<List<Theory>> lesson(@Query("=") int id);
    }

    public static List<Theory> getTheoryLessons() {

        Gson gson = new Gson();
        List<Theory> lessons = null;
        StrictMode.setThreadPolicy(gfgPolicy);

        try {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(QUESTIONS_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

            TheoryHelper.IAllTheory ls = retrofit.create(TheoryHelper.IAllTheory.class);
            Call<List<Theory>> call = ls.lessons();
            lessons = call.execute().body();

            Log.i("Request to API", "Theory: FINE! Size = " + lessons.size());
        } catch (Exception e) {
            Log.i("API/Theory: ", e.getMessage());
        }

        return lessons;
    }
    public static List<Theory> getLessonsByID(int id) {

        Gson gson = new Gson();
        List<Theory> lessons = null;
        StrictMode.setThreadPolicy(gfgPolicy);

        try {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(QUESTIONS_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

            TheoryHelper.ITheoryByID ls = retrofit.create(TheoryHelper.ITheoryByID.class);
            Call<List<Theory>> call = ls.lesson(id);
            lessons = call.execute().body();

            Log.i("Request to API", "Theory: FINE! Size = " + lessons.size());
        } catch (Exception e) {
            Log.i("API/Theory: ", e.getMessage());
        }

        return lessons;
    }
    public static List<Theory> getLessonsByTheme(int id) {

        Gson gson = new Gson();
        List<Theory> lessons = null;
        StrictMode.setThreadPolicy(gfgPolicy);

        try {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(QUESTIONS_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

            TheoryHelper.ITheoryByTheme ls = retrofit.create(TheoryHelper.ITheoryByTheme.class);
            Call<List<Theory>> call = ls.lesson(id);
            lessons = call.execute().body();

            Log.i("Request to API", "Theory: FINE! Size = " + lessons.size());
        } catch (Exception e) {
            Log.i("API/Theory: ", e.getMessage());
        }

        return lessons;
    }
}
