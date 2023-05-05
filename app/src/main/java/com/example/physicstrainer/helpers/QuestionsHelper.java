package com.example.physicstrainer.helpers;

import android.os.StrictMode;
import android.util.Log;
import com.example.physicstrainer.serialize.Image;
import com.example.physicstrainer.serialize.Question;
import com.google.gson.Gson;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuestionsHelper {

    private static final String QUESTIONS_URL = "http://2.59.37.134:8080";
    private static final StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    // интерфейсы GET-запросов
    private interface Questions {
        @GET("/get/questions")
        Call<List<Question>> questions();
    }
    private interface QuestionsImage {
        @GET("/get/question/image")
        Call<Image> image(@Query("=") int id);
    }
    private interface questionByID {
        @GET("/get/question")
        Call<List<Question>> questions(@Query("=") int id);
    }
    private interface questionsByBlock {
        @GET("/get/questions/block")
        Call<List<Question>> questions(@Query("=") int id);
    }
    private interface deleteQuestion {

        @DELETE("/delete/question")
        Call<ResponseBody> deleteQuestion(@Query("=") int id);
    }

    /**
     * Метод для получения всех вопросов
     */
    public static List<Question> getAllQuestions() {

        Gson gson = new Gson();
        List<Question> questions = null;
        StrictMode.setThreadPolicy(gfgPolicy);

        try {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(QUESTIONS_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

            QuestionsHelper.Questions qs = retrofit.create(QuestionsHelper.Questions.class);
            Call<List<Question>> call = qs.questions();
            questions = call.execute().body();

            Log.i("Request to API", "Questions: FINE! Size = " + questions.size());
        } catch (Exception e) {
            Log.i("API/questions", e.getMessage());
        }

        return questions;
    }

    /**
     * Метод для получения вопроса по его ID
     * @param id ID вопроса
     */
    protected static List<Question> getQuestionByID(int id) {

        Gson gson = new Gson();
        List<Question> questions = null;
        StrictMode.setThreadPolicy(gfgPolicy);

        try {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(QUESTIONS_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

            QuestionsHelper.questionByID qs = retrofit.create(QuestionsHelper.questionByID.class);
            Call<List<Question>> call = qs.questions(id);
            questions = call.execute().body();

            Log.i("Request to API", "getQuestion: FINE!");
        } catch (Exception e) {
            Log.i("API/getQuestionError - ", e.getMessage());
        }

        return questions;
    }

    /**
     * Метод для получения вопросов по блоку
     * @param id ID блока
     */
    protected static List<Question> getQuestionByBlock(int id) {

        Gson gson = new Gson();
        List<Question> questions = null;
        StrictMode.setThreadPolicy(gfgPolicy);

        try {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(QUESTIONS_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

            QuestionsHelper.questionsByBlock qs = retrofit.create(QuestionsHelper.questionsByBlock.class);
            Call<List<Question>> call = qs.questions(id);
            questions = call.execute().body();

            Log.i("Request to API", "getQuestionByBlock: FINE!");
        } catch (Exception e) {
            Log.i("API/getQuestionByBlockError - ", e.getMessage());
        }

        return questions;
    }

    /**
     * Метод для получения картинки под ворпос
     * @param id ID вопроса
     */
    protected static Image getQuestionImage(int id) {

        Gson gson = new Gson();
        Image image = null;
        StrictMode.setThreadPolicy(gfgPolicy);

        try {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(QUESTIONS_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

            QuestionsHelper.QuestionsImage qs = retrofit.create(QuestionsHelper.QuestionsImage.class);
            Call<Image> call = qs.image(id);
            image = call.execute().body();

            Log.i("Request to API", "GetQuestionImage: FINE!");
        } catch (Exception e) {
            Log.i("API/GetQuestionImageError - ", e.getMessage());
        }

        return image;
    }

    /**
     * Метод для удаления вопроса
     * @param id ID вопроса
     */
    // Удаление блока по ID
    static void deleteQuestion(int id){

        StrictMode.setThreadPolicy(gfgPolicy);

        try  {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(QUESTIONS_URL)
                            .build();

            QuestionsHelper.deleteQuestion qs = retrofit.create(QuestionsHelper.deleteQuestion.class);
            Call<ResponseBody> call = qs.deleteQuestion(id);

            Log.i("Request to API", "DeleteQuestion: FINE! Message = " + call.execute().message());
        } catch (Exception e) {
            Log.i("API/deleteQuestionError - ", e.getMessage());
        }
    }
}
