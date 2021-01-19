package com.example.facebookapp.ui.home.activity;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void checkToken(String token) {
        if (token == null)
            view.returnLogin();
    }

    @Override
    public void getInfo(String token, String userId) {

    }
}
