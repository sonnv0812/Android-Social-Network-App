package com.example.facebookapp.data.repository.singup.confirm;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.AccountModel;

public interface ConfirmRepository {
    void getSignUp(String user, String password, String uuid, OnDataLoadedListener<String> callback);
}
