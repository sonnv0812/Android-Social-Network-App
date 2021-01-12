package com.example.facebookapp.ui.friend.suggest;

import com.example.facebookapp.data.repository.friend.suggest.SuggestRepository;

public class SuggestFriendPresenter implements SuggestFriendContract.Presenter {

    private SuggestFriendContract.View view;
    private SuggestRepository repository;

    public SuggestFriendPresenter(SuggestFriendContract.View view, SuggestRepository repository) {
        this.view = view;
        this.repository = repository;
    }
}
