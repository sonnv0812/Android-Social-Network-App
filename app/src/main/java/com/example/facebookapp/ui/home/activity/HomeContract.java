package com.example.facebookapp.ui.home.activity;

public interface HomeContract {
    interface View {
        void returnLogin();
    }

    interface Presenter {
        void checkToken(String token);
    }
}
