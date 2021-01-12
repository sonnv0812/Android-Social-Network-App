package com.example.facebookapp.data.repository.login;

import com.example.facebookapp.R;
import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.account.AccountModel;
import com.example.facebookapp.data.model.BaseResponse;
import com.example.facebookapp.network.ApiService;
import com.example.facebookapp.network.ResponseCode;
import com.example.facebookapp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepositoryImpl implements LoginRepository {

    private final ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

    @Override
    public void loginAction(String phone, String password, String uuid, OnDataLoadedListener<AccountModel> callback) {
        apiService.login(phone, password, uuid).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful())
                    switch (response.body().getCode()) {
                        case ResponseCode.OK:
                            AccountModel account = new AccountModel(
                                    response.body().getAccountModel().getId(),
                                    response.body().getAccountModel().getUsername(),
                                    response.body().getAccountModel().getAvatarLink(),
                                    response.body().getAccountModel().getToken()
                            );
                            callback.onSuccess(account);
                            break;
                        case ResponseCode.USER_IS_NOT_INVALID:
                            callback.onFailure(new Exception(String.valueOf(R.string.error_login_invalid)));
                            break;
                        case ResponseCode.PARAMETER_TYPE_IS_INVALID:
                            callback.onFailure(new Exception(String.valueOf(R.string.error_input_type_is_invalid)));
                            break;
                    }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
}
