package com.example.facebookapp.network;

import android.renderscript.ScriptIntrinsicYuvToRGB;

import com.example.facebookapp.data.model.AccountModel;
import com.example.facebookapp.data.model.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
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

    @POST("add_post")
    Call<BaseResponse> addPost(@Query("token") String token,
                               @Query("image") List<String> image,
                               @Query("video") String video,
                               @Query("described") String described,
                               @Query("status") String status);

    @POST("get_post")
    Call<BaseResponse> getPost(@Query("token") String token,
                               @Query("id") int id);

    @POST("get_list_posts")
    Call<BaseResponse> getListPosts(@Query("token") String token,
                                    @Query("user_id") String userId,
                                    @Query("in_campaign") String inCampaign,
                                    @Query("campaign_id") String campaignId,
                                    @Query("latitude") String latitude,
                                    @Query("longitude") String longitude,
                                    @Query("last_id") String lastId,
                                    @Query("index") String index,
                                    @Query("count") String count);
}
