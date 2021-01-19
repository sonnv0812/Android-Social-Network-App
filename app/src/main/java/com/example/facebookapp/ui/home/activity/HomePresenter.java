package com.example.facebookapp.ui.home.activity;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.account.AccountModel;
import com.example.facebookapp.data.repository.home.home.HomeRepository;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private HomeRepository repository;

    public HomePresenter(HomeContract.View view, HomeRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void checkToken(String token) {
        if (token == null)
            view.returnLogin();
    }

    @Override
    public void getInfo(String token, String userId) {
        repository.getMyInfo(token, userId, new OnDataLoadedListener<AccountModel>() {
            @Override
            public void onSuccess(AccountModel data) {
                view.saveUserInfo(data);
            }

            @Override
            public void onFailure(Exception exception) {

            }
        });
    }
}
