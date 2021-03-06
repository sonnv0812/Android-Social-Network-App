package com.example.facebookapp.ui.login;

import com.example.facebookapp.data.model.account.AccountModel;

public interface LoginContract {
    interface View {
        void showPhoneError(int msgResId);

        void showPasswordError(int msgResId);

        void showError(int msgResId);

        void nextHome(AccountModel account);
    }

    interface Presenter {
        void handleLogin(String phone, String password, String uuid);

        void checkInputPhoneEmail(String phoneEmail);

        void checkInputPassword(String password, String phone);
    }
}
