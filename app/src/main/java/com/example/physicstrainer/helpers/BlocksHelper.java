package com.example.physicstrainer.helpers;

import android.os.StrictMode;
import android.util.Log;
import com.example.physicstrainer.serialize.Block;
import com.google.firebase.annotations.concurrent.Background;
import com.google.gson.Gson;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Хелпер для получения блоков заданий из API
 */
public class BlocksHelper {

    private static final String URL = "http://2.59.37.134:8080";

    private interface Blocks {
        @GET("/get/blocks")
        Call<List<Block>> blocks();
    }
    private interface blockByID {
        @GET("/get/block={id}")
        Call<Block> blocks(@Path("id") int id);
    }
    private interface deleteBlock {
        @GET("/delete/block")
        Call delBlock(@Query("=") int id);
    }

    private static StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    /**
     * Метод для получения всех существующих блоков заданий
     * @return List с блоками и заданиями
     */
    @Background
    public static List<Block> getAllBlocks(){

        Gson gson = new Gson();
        List<Block> blocks = null;
        StrictMode.setThreadPolicy(gfgPolicy);

        try  {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

            BlocksHelper.Blocks bl = retrofit.create(BlocksHelper.Blocks.class);
            Call<List<Block>> call = bl.blocks();
            blocks = call.execute().body();

            Log.i("Request to API", "Blocks: FINE! Size = " + blocks.size());
        } catch (Exception e) {
            Log.i("API/Blocks", e.getMessage());
        }

        return blocks;
    }

    /**
     * Метод для получения конкретного блока по его ID
     * @param id ID блока
     * @return List с блоком
     */
    // Получение конкретного блока по его ID
    @Background
    public static Block getBlockByID(int id){

        Gson gson = new Gson();
        Block blocks = null;
        StrictMode.setThreadPolicy(gfgPolicy);

        try  {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

            BlocksHelper.blockByID bl = retrofit.create(blockByID.class);
            Call<Block> call = bl.blocks(id);
            blocks = call.execute().body();

            Log.i("Request to API", "BlocksByID: FINE!");
        } catch (Exception e) {
            Log.i("API/BlocksByID", e.getMessage());
        }

        return blocks;
    }

    /**
     * Метод для удаления блока
     * @param id ID блока
     */
    // Удаление блока по ID
    static void deleteBlock(int id){

        StrictMode.setThreadPolicy(gfgPolicy);

        try  {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(URL)
                            .build();

            BlocksHelper.deleteBlock bl = retrofit.create(deleteBlock.class);
            Call<List<Block>> call = bl.delBlock(id);

            Log.i("Request to API", "DeleteBlock: FINE! Message = " + call.execute().message());
        } catch (Exception e) {
            Log.i("API/deleteBlock", e.getMessage());
        }
    }
}
