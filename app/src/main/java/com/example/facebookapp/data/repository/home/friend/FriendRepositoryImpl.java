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
        apiService.getRequestedFriend(token, index, count).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                int total = Integer.parseInt(response.body().getRequestFriend().getTotal());
                if (response.isSuccessful()) {
                    switch (response.body().getCode()) {
                        case ResponseCode.OK:
//                        while ()
                            callback.onSuccess(friendResponse);
                            break;
                    }

                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getUserFriend(String token, String userId, int index, int count, OnDataLoadedListener<List<Friend>> callback) {

    }
}
