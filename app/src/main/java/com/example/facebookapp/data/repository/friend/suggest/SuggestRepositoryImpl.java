package com.example.facebookapp.data.repository.friend.suggest;

import android.util.Log;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.friend.BaseFriendResponse;
import com.example.facebookapp.data.model.friend.BaseRequestFriend;
import com.example.facebookapp.data.model.friend.Friend;
import com.example.facebookapp.network.ApiService;
import com.example.facebookapp.network.ResponseCode;
import com.example.facebookapp.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuggestRepositoryImpl implements SuggestRepository {

    private ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

    @Override
    public void getSuggestFriend(String token, int index, int count, OnDataLoadedListener<List<Friend>> callback) {
        List<Friend> friendResponse = new ArrayList<>();
        apiService.getListSuggestedFriend(token, index, count).enqueue(new Callback<BaseFriendResponse>() {
            @Override
            public void onResponse(Call<BaseFriendResponse> call, Response<BaseFriendResponse> response) {
                if (response.isSuccessful()) {
                    switch (response.body().getCode()) {
                        case ResponseCode.OK:
                            Log.v("FRIEND", "Đã nhận dữ liệu thành công");
                            int i = 0;
                            while (i < response.body().getRequestFriend().getListUser().size()) {
                                friendResponse.add(new Friend(
                                        response.body().getRequestFriend().getListUser().get(i).getId(),
                                        response.body().getRequestFriend().getListUser().get(i).getUsername(),
                                        response.body().getRequestFriend().getListUser().get(i).getAvatar(),
                                        response.body().getRequestFriend().getListUser().get(i).getSameFriend()
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
            public void onFailure(Call<BaseFriendResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getRequestFriend(String token, String userId, OnDataLoadedListener<String> callback) {
        apiService.setRequestFriend(token, userId).enqueue(new Callback<BaseRequestFriend>() {
            @Override
            public void onResponse(Call<BaseRequestFriend> call, Response<BaseRequestFriend> response) {
                switch (response.body().getCode()) {
                    case ResponseCode.OK:
                        callback.onSuccess("Gửi yêu cầu kết bạn thành công");
                        break;
                    default:
                        callback.onFailure(new Exception("Lỗi"));
                        break;
                }
            }

            @Override
            public void onFailure(Call<BaseRequestFriend> call, Throwable t) {
                callback.onFailure(new Exception("Lỗi"));
            }
        });
    }
}
