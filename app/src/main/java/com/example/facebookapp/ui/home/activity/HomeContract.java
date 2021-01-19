package com.example.facebookapp.ui.home.activity;

import com.example.facebookapp.data.model.account.AccountModel;

public interface HomeContract {
    interface View {
        void returnLogin();

        void saveUserInfo(AccountModel account);
    }

    interface Presenter {
        void checkToken(String token);

        void getInfo(String token, String userId);
    }
}
