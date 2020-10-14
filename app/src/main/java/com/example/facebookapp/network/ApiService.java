package com.example.facebookapp.network;

import com.example.facebookapp.model.AccountModel;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("signup")
    Call<AccountModel> signUp(@Query("phonenumber") String phoneNumber,
                              @Query("password") String password,
                              @Query("uuid") String uuid);

    @POST("login")
    Call<AccountModel> login(@Query("phonenumber") String phoneNumber,
                             @Query("password") String password,
                             @Query("uuid") String uuid);
}
