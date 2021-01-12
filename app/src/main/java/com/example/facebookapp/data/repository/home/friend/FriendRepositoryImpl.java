package com.example.facebookapp.data.repository.home.friend;

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

public class FriendRepositoryImpl implements FriendRepository {

    private ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

    @Override
    public void getRequestFriend(String token, int index, int count, OnDataLoadedListener<List<Friend>> callback) {
        List<Friend> friendResponse = new ArrayList<>();
        apiService.getRequestedFriend(token, index, count).enqueue(new Callback<List<BaseResponse>>() {
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
                            callback.onSuccess(friendResponse);
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BaseResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void setAccept(String token, String userId, boolean isAccept, OnDataLoadedListener<String> callback) {
        apiService.setAcceptFriend(token, userId, String.valueOf(isAccept)).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
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
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
}
