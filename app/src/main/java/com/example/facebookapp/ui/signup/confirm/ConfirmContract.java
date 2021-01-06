package com.example.facebookapp.ui.signup.confirm;

public interface ConfirmContract {
    interface View {
        void showError(int msgResId);

        void nextLogin();

        void updateCreateUI(Boolean isPhone);
    }

    interface Presenter {
        void handleSignUp(String confirmCode, String phone, String mail, String password, String uuid);

        void handleCreateUI(String phone, String mail);
    }
}
