package com.example.facebookapp.network;

import com.example.facebookapp.data.model.AccountModel;
import com.example.facebookapp.data.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("signup")
    Call<BaseResponse> signUp(@Query("phonenumber") String phoneNumber,
                              @Query("password") String password,
                              @Query("uuid") String uuid);

    @POST("login")
    Call<BaseResponse> login(@Query("phonenumber") String phoneNumber,
                             @Query("password") String password,
                             @Query("uuid") String uuid);

    @POST("logout")
    Call<BaseResponse> logout(@Query("token") String token);
}
