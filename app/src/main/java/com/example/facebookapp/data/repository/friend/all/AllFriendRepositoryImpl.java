package com.example.facebookapp.data.repository.friend.all;

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

public class AllFriendRepositoryImpl implements AllFriendRepository {

    private ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

    @Override
    public void getUserFriend(String token, String userId, int index, int count, OnDataLoadedListener<List<Friend>> callback) {
        List<Friend> friendResponse = new ArrayList<>();
        apiService.getUserFriend(userId, token, index, count).enqueue(new Callback<FriendResponse>() {
            @Override
            public void onResponse(Call<FriendResponse> call, Response<FriendResponse> response) {
                if (response.isSuccessful()) {
                    int total = Integer.parseInt(response.body().getRequestFriend().getTotal());
                    switch (response.body().getCode()) {
                        case ResponseCode.OK:
                            int i = 0;
                            while (i < total) {
                                friendResponse.add(new Friend(
                                        response.body().getRequestFriend().getFriends().get(i).getId(),
                                        response.body().getRequestFriend().getFriends().get(i).getUsername(),
                                        response.body().getRequestFriend().getFriends().get(i).getAvatar(),
                                        response.body().getRequestFriend().getFriends().get(i).getSameFriend()
                                ));
                                i++;
                            }
                            callback.onSuccess(friendResponse);
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<FriendResponse> call, Throwable t) {

            }
        });
    }
}
