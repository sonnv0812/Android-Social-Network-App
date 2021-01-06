package com.example.facebookapp.data.repository.singup.confirm;

import com.example.facebookapp.R;
import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.AccountModel;
import com.example.facebookapp.network.ApiService;
import com.example.facebookapp.network.ResponseCode;
import com.example.facebookapp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmRepositoryImpl implements ConfirmRepository {

    private final ApiService api = RetrofitClient.getInstance().create(ApiService.class);

    @Override
    public void getSignUp(String user, String password, String uuid, OnDataLoadedListener<String> callback) {
        api.signUp(user, password, uuid).enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                if (response.isSuccessful()) {
                    switch (response.body().getCode()) {
                        case ResponseCode.OK:
                            String token = response.body().getToken();
                            callback.onSuccess(token);
                            break;
                        case ResponseCode.USER_EXISTED:
                            callback.onFailure(new Exception(String.valueOf(R.string.error_user_existed)));
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {

            }
        });
    }
}
