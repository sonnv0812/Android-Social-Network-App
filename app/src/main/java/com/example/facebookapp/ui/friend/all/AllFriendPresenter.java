package com.example.facebookapp.ui.friend.all;

import com.example.facebookapp.data.repository.friend.all.AllFriendRepository;

public class AllFriendPresenter implements AllFriendContract.Presenter {

    private AllFriendContract.View view;
    private AllFriendRepository repository;

    public AllFriendPresenter(AllFriendContract.View view, AllFriendRepository repository) {
        this.view = view;
        this.repository = repository;
    }
}
