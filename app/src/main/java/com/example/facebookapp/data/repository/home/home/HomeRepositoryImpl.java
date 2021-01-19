package com.example.facebookapp.data.repository.home.home;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.account.AccountModel;
import com.example.facebookapp.data.model.account.BaseUserResponse;
import com.example.facebookapp.network.ApiService;
import com.example.facebookapp.network.ResponseCode;
import com.example.facebookapp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepositoryImpl implements HomeRepository {

    private final ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

    @Override
    public void getMyInfo(String token, String userId, OnDataLoadedListener<AccountModel> callback) {

        apiService.getUserInfo(token, userId).enqueue(new Callback<BaseUserResponse>() {
            @Override
            public void onResponse(Call<BaseUserResponse> call, Response<BaseUserResponse> response) {
                switch (response.body().getCode()) {
                    case ResponseCode.OK:
                        AccountModel accountModel = new AccountModel(
                                response.body().getAccountModel().getId(),
                                response.body().getAccountModel().getUsername(),
                                response.body().getAccountModel().getAvatarLink()
                        );
                        callback.onSuccess(accountModel);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<BaseUserResponse> call, Throwable t) {

            }
        });
    }
}
