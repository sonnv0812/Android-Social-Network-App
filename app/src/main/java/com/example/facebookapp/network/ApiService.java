package com.example.facebookapp.network;

import com.example.facebookapp.data.model.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    /**
     *
     * @param phoneNumber
     * @param password
     * @param uuid
     * @return
     */
    @POST("signup")
    Call<BaseResponse> signUp(@Query("phonenumber") String phoneNumber,
                              @Query("password") String password,
                              @Query("uuid") String uuid);

    /**
     * Api login to facebook
     *
     * @param phoneNumber
     * @param password
     * @param uuid
     * @return
     */
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

    @POST("get_requested_friends")
    Call<List<BaseResponse>> getRequestedFriend(@Query("token") String token,
                                          @Query("index") int index,
                                          @Query("count") int count);

    @POST("get_user_friends")
    Call<BaseResponse> getUserFriend(@Query("user_id") String userId,
                                     @Query("token") String token,
                                     @Query("index") int index,
                                     @Query("count") int count);

    @POST("set_accept_friend")
    Call<BaseResponse> setAcceptFriend(@Query("token") String token,
                                       @Query("user_id") String user_id,
                                       @Query("is_accept") String isAccept);


    @POST("get_list_suggested_friends")
    Call<BaseResponse> getListSuggestedFriend(@Query("token") String token,
                                              @Query("index") int index,
                                              @Query("count") int count);

    @POST("change_password")
    Call<BaseResponse> changePassword(@Query("token") String token,
                                      @Query("password") String password,
                                      @Query("new_password") String newPassword);
}
