package com.example.facebookapp.data.repository.home.home;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.account.AccountModel;

public interface HomeRepository {
    void getMyInfo(String token, String userId, OnDataLoadedListener<AccountModel> callback);
}
