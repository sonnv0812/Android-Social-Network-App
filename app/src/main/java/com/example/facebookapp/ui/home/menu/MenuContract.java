package com.example.facebookapp.ui.home.menu;

public interface MenuContract {
    interface View {
        void showError(int msgResId);

        void successLogout();
    }

    interface Presenter {
        void handleLogout(String token);
    }
}
