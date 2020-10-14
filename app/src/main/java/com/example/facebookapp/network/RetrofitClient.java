package com.example.facebookapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://project-facebook-clone.herokuapp.com/it4788/user/";

    private static Retrofit retrofitClient = null;

    public static Retrofit getInstance() {
        if (retrofitClient == null)
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofitClient;
    }
}
