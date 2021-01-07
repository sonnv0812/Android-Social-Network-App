package com.example.facebookapp.data.repository.login;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.AccountModel;

public interface LoginRepository {
    void loginAction(String phone, String password, String uuid, OnDataLoadedListener<AccountModel> callback);
}
