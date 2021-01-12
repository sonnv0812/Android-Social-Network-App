package com.example.facebookapp.ui.home.friend;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.friend.Friend;
import com.example.facebookapp.data.repository.home.friend.FriendRepository;

import java.util.List;

public class FriendPresenter implements FriendContract.Presenter {

    private FriendContract.View view;
    private FriendRepository repository;

    public FriendPresenter(FriendContract.View view, FriendRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void handleRequestFriend(String token) {
        repository.getRequestFriend(token, 0, 20, new OnDataLoadedListener<List<Friend>>() {
            @Override
            public void onSuccess(List<Friend> data) {
                view.updateData(data);
            }

            @Override
            public void onFailure(Exception exception) {
                view.showMessage(Integer.parseInt(exception.getMessage()));
            }
        });
    }

    @Override
    public void handleGetUserFriend() {

    }

    @Override
    public void handleAcceptFriend(String userId) {

    }

    @Override
    public void handleDeleteRequest(String userId) {

    }
}
