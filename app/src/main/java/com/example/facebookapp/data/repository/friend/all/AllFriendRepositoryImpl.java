package com.example.facebookapp.data.repository.friend.all;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.BaseResponse;
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
        apiService.getUserFriend(userId, token, index, count).enqueue(new Callback<List<BaseResponse>>() {
            @Override
            public void onResponse(Call<List<BaseResponse>> call, Response<List<BaseResponse>> response) {
                if (response.isSuccessful()) {
                    int total = Integer.parseInt(response.body().get(0).getRequestFriend().getTotal());
                    switch (response.body().get(0).getCode()) {
                        case ResponseCode.OK:
                            int i = 0;
                            while (i < total) {
                                friendResponse.add(new Friend(
                                        response.body().get(i).getRequestFriend().getRequest().getId(),
                                        response.body().get(i).getRequestFriend().getRequest().getUsername(),
                                        response.body().get(i).getRequestFriend().getRequest().getAvatar(),
                                        response.body().get(i).getRequestFriend().getRequest().getSameFriend(),
                                        response.body().get(i).getRequestFriend().getRequest().getCreated()
                                ));
                                i++;
                            }
                            break;
                        default:
                            break;
                    }
                }
                callback.onSuccess(friendResponse);
            }

            @Override
            public void onFailure(Call<List<BaseResponse>> call, Throwable t) {

            }
        });
    }
}
