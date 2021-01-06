package com.example.facebookapp.ui.signup.mail;

public interface EnterMailContract {
    interface View {
        void showError(int msgResId);
    }

    interface Presenter {
        void checkValidateMail(String mail);
    }
}
