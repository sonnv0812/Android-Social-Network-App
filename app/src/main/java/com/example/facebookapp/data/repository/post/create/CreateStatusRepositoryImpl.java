package com.example.facebookapp.data.repository.post.create;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.post.BasePostResponse;
import com.example.facebookapp.network.ApiService;
import com.example.facebookapp.network.ResponseCode;
import com.example.facebookapp.network.RetrofitClient;

import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateStatusRepositoryImpl implements CreateStatusRepository {

    private final ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

    @Override
    public void getApiAddPost(String token, String described, String status, List<File> image, File video, OnDataLoadedListener<String> callback) {
        apiService.addPost(token, described, status).enqueue(new Callback<BasePostResponse>() {
            @Override
            public void onResponse(Call<BasePostResponse> call, Response<BasePostResponse> response) {
                switch (response.body().getCode()) {
                    case ResponseCode.OK:
                        callback.onSuccess("Đăng bài thành công!");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<BasePostResponse> call, Throwable t) {

            }
        });
    }
}
