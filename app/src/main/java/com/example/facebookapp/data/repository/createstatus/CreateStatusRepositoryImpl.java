package com.example.facebookapp.data.repository.createstatus;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.BaseResponse;
import com.example.facebookapp.network.ApiService;
import com.example.facebookapp.network.ResponseCode;
import com.example.facebookapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateStatusRepositoryImpl implements CreateStatusRepository {

    private final ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

    @Override
    public void getApiAddPost(String token, List<String> image, String video, String described, String status, OnDataLoadedListener<String> callback) {
        apiService.addPost(token, null, null, described, null).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful())
                    switch (response.body().getCode()) {
                        case ResponseCode.OK:
                            callback.onSuccess("Đăng bài thành công");
                            break;
                    }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
}
