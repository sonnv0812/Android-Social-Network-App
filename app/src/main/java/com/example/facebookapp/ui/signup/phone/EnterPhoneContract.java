package com.example.facebookapp.ui.signup.phone;

public interface EnterPhoneContract {
    interface View {
        void showError(int msgResId);
    }

    interface Presenter {
        void checkValidatePhone(String phone);
    }
}
