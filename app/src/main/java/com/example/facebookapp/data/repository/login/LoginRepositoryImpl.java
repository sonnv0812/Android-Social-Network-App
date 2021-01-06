package com.example.facebookapp.data.repository.login;

import com.example.facebookapp.R;
import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.AccountModel;
import com.example.facebookapp.network.ApiService;
import com.example.facebookapp.network.ResponseCode;
import com.example.facebookapp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepositoryImpl implements LoginRepository {

    private final ApiService loginApi = RetrofitClient.getInstance().create(ApiService.class);

    @Override
    public void loginAction(String phone, String password, OnDataLoadedListener<AccountModel> callback) {
        loginApi.login(phone, password, "123456").enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                if (response.isSuccessful()) {
                    switch (response.body().getCode()) {
                        case ResponseCode.OK :
                            AccountModel account = new AccountModel(
                                    response.body().getId(),
                                    response.body().getUsername(),
                                    response.body().getAvatarLink(),
                                    response.body().getToken()
                            );
                            callback.onSuccess(account);
                            break;
                        case ResponseCode.USER_IS_NOT_INVALID:
                            callback.onFailure(new Exception(String.valueOf(R.string.error_login_invalid)));
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {
                callback.onFailure(new Exception(String.valueOf(R.string.error_no_network)));
            }
        });
    }
}
