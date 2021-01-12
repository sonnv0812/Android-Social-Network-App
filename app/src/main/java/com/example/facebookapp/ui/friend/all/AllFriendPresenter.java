package com.example.facebookapp.ui.friend.all;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.friend.Friend;
import com.example.facebookapp.data.repository.friend.all.AllFriendRepository;

import java.util.List;

public class AllFriendPresenter implements AllFriendContract.Presenter {

    private AllFriendContract.View view;
    private AllFriendRepository repository;

    public AllFriendPresenter(AllFriendContract.View view, AllFriendRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void handleGetUserFriend(String token, String userId) {
        repository.getUserFriend(token, userId, 0, 20, new OnDataLoadedListener<List<Friend>>() {
            @Override
            public void onSuccess(List<Friend> data) {
                view.updateFriendData(data);
            }

            @Override
            public void onFailure(Exception exception) {
                view.showMessage(Integer.parseInt(exception.getMessage()));
            }
        });
    }
}
