package com.example.physicstrainer.helpers;

import android.os.StrictMode;
import android.util.Log;

import com.example.physicstrainer.serialize.Theme;
import com.example.physicstrainer.serialize.Theory;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ThemeHelper {

    private static final String QUESTIONS_URL = "http://2.59.37.134:8080";
    private static final StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();


    private interface IAllThemes {
        @GET("/get/theories")
        Call<List<Theme>> theme();
    }

    public static List<Theme> getTheme() {

        Gson gson = new Gson();
        List<Theme> theme = null;
        StrictMode.setThreadPolicy(gfgPolicy);

        try {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(QUESTIONS_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

            ThemeHelper.IAllThemes ls = retrofit.create(ThemeHelper.IAllThemes.class);
            Call<List<Theme>> call = ls.theme();
            theme = call.execute().body();

            Log.i("Request to API", "Lessons: FINE! Size = " + theme.size());
        } catch (Exception e) {
            Log.i("API/Lessons: ", e.getMessage());
        }

        return theme;
    }
}
