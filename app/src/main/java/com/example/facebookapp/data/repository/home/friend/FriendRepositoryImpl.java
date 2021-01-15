package com.example.facebookapp.data.repository.home.friend;

import android.util.Log;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.FriendResponse;
import com.example.facebookapp.data.model.friend.Friend;
import com.example.facebookapp.network.ApiService;
import com.example.facebookapp.network.ResponseCode;
import com.example.facebookapp.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendRepositoryImpl implements FriendRepository {

    private final ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

    @Override
    public void getRequestFriend(String token, int index, int count, OnDataLoadedListener<List<Friend>> callback) {
        List<Friend> friendResponse = new ArrayList<>();
        apiService.getRequestedFriend(token, index, count).enqueue(new Callback<FriendResponse>() {
            @Override
            public void onResponse(Call<FriendResponse> call, Response<FriendResponse> response) {
                Log.v("FRIEND", response.body().toString());
                if (response.isSuccessful()) {
                    int total = Integer.parseInt(response.body().getRequestFriend().getTotal());
                    switch (response.body().getCode()) {
                        case ResponseCode.OK:
                            int i = 0;
                            while (i < total) {
                                friendResponse.add(new Friend(
                                        response.body().getRequestFriend().getListUser().get(i).getId(),
                                        response.body().getRequestFriend().getListUser().get(i).getUsername(),
                                        response.body().getRequestFriend().getListUser().get(i).getAvatar(),
                                        response.body().getRequestFriend().getListUser().get(i).getSameFriend(),
                                        "1 ngày trước"
                                ));
                                i++;
                            }
                            callback.onSuccess(friendResponse);
                            break;
                        default:
                            Log.v("FRIEND", "Có lỗi này");
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<FriendResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void setAccept(String token, String userId, boolean isAccept, OnDataLoadedListener<String> callback) {
        apiService.setAcceptFriend(token, userId, String.valueOf(isAccept)).enqueue(new Callback<FriendResponse>() {
            @Override
            public void onResponse(Call<FriendResponse> call, Response<FriendResponse> response) {
                if (response.isSuccessful())
                    switch (response.body().getCode()) {
                        case ResponseCode.OK:
                            callback.onSuccess(userId);
                            break;
                        default:
                            break;
                    }
            }

            @Override
            public void onFailure(Call<FriendResponse> call, Throwable t) {

            }
        });
    }
}
