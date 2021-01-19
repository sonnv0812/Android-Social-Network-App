package com.example.facebookapp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitPostClient {
    /**
     * Base url của api
     */
    private static final String BASE_URL = "https://project-facebook-clone.herokuapp.com/it4788/";

    /**
     * Khi chưa có đối tượng retrofit2 thì khởi tạo bằng null
     */
    private static Retrofit retrofit = null;

    /**
     * Khởi tạo chỉ một đối tượng retrofit dùng để call api
     *
     * @return Retrofit là đối tượng dùng để gọi API
     */
    public static Retrofit getInstance() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        Gson gson = gsonBuilder.create();
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        return retrofit;
    }
}
