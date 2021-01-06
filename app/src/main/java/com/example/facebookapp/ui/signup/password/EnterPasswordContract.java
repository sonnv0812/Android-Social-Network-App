package com.example.facebookapp.ui.signup.password;

public interface EnterPasswordContract {
    interface View {
        void showPasswordError(int msgResId);

        void showConfirmPassError(int msgResId);

        void nextConfirm();
    }

    interface Presenter {
        void checkValidatePassword(String password);

        void checkConfirmPassword(String password, String confirmPassword);
    }
}
