package com.example.facebookapp.ui.profile.my;

import com.example.facebookapp.data.repository.profile.my.MyProfileRepository;

public class MyProfilePresenter implements MyProfileContract.Presenter {

    private MyProfileContract.View view;
    private MyProfileRepository repository;

    public MyProfilePresenter(MyProfileContract.View view, MyProfileRepository repository) {
        this.view = view;
        this.repository = repository;
    }

}
