package com.example.physicstrainer.helpers;

import android.os.StrictMode;
import android.util.Log;

import com.example.physicstrainer.serialize.Block;
import com.example.physicstrainer.serialize.User;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class UsersHelper {

    private interface IGetUser {
        @GET("/get/user={id}")
        Call<User> getUser(@Path("id") int id);
    }
    private interface IGetUsers {
        @GET("/get/users")
        Call<List<User>> getUsers();
    }

    private static StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    private static final String URL = "http://2.59.37.134:8080";

    public static User getUser(int id){

        Gson gson = new Gson();
        User user = null;
        StrictMode.setThreadPolicy(gfgPolicy);

        try  {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

            UsersHelper.IGetUser usr = retrofit.create(UsersHelper.IGetUser.class);
            Call<User> call = usr.getUser(id);
            user = call.execute().body();

            Log.i("Request to API", "User: FINE! UserName = " + user.getName() + " UserID = " + user.getID());
        } catch (Exception e) {
            Log.i("API/UsersError - ", e.getMessage());
        }

        return user;
    }
    public static List<User> getUsers(){

        Gson gson = new Gson();
        List<User> users = null;
        StrictMode.setThreadPolicy(gfgPolicy);

        try  {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

            UsersHelper.IGetUsers usr = retrofit.create(UsersHelper.IGetUsers.class);
            Call<List<User>> call = usr.getUsers();
            users = call.execute().body();

            Log.i("Request to API", "Users - FINE. UsersCount = " + users.size());
        } catch (Exception e) {
            Log.i("API/UsersError - ", e.getMessage());
        }

        return users;
    }
}
