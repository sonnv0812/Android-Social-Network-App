package com.example.facebookapp.network;

import com.example.facebookapp.data.model.account.BaseUserResponse;
import com.example.facebookapp.data.model.friend.BaseFriendResponse;
import com.example.facebookapp.data.model.post.BasePostResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("user/signup")
    Call<BaseUserResponse> signUp(@Query("phonenumber") String phoneNumber,
                                  @Query("password") String password,
                                  @Query("uuid") String uuid);

    @POST("user/login")
    Call<BaseUserResponse> login(@Query("phonenumber") String phoneNumber,
                                 @Query("password") String password,
                                 @Query("uuid") String uuid);

    @POST("user/logout")
    Call<BaseUserResponse> logout(@Query("token") String token);

    @POST("user/get_requested_friends")
    Call<BaseFriendResponse> getRequestedFriend(@Query("token") String token,
                                                @Query("index") int index,
                                                @Query("count") int count);

    @POST("user/get_user_friends")
    Call<BaseFriendResponse> getUserFriend(@Query("user_id") String userId,
                                           @Query("token") String token,
                                           @Query("index") int index,
                                           @Query("count") int count);

    @POST("user/set_accept_friend")
    Call<BaseFriendResponse> setAcceptFriend(@Query("token") String token,
                                             @Query("user_id") String user_id,
                                             @Query("is_accept") String isAccept);


    @POST("user/get_list_suggested_friends")
    Call<BaseFriendResponse> getListSuggestedFriend(@Query("token") String token,
                                                    @Query("index") int index,
                                                    @Query("count") int count);

    @POST("change_password")
    Call<BaseFriendResponse> changePassword(@Query("token") String token,
                                            @Query("password") String password,
                                            @Query("new_password") String newPassword);


    @POST("post/add_post")
    Call<BasePostResponse> addPost(@Query("token") String token,
                                   @Query("described") String described,
                                   @Query("status") String status);

    @POST("post/get_post")
    Call<BasePostResponse> getPost(@Query("token") String token,
                                   @Query("id") int id);

    @POST("post/get_list_post")
    Call<BasePostResponse> getListPosts(@Query("token") String token,
                                        @Query("user_id") String userId,
                                        @Query("last_id") String lastId,
                                        @Query("index") int index,
                                        @Query("count") int count);
}
