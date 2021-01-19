package com.example.facebookapp.ui.friend.suggest;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.friend.Friend;
import com.example.facebookapp.data.repository.friend.suggest.SuggestRepository;

import java.util.List;

public class SuggestFriendPresenter implements SuggestFriendContract.Presenter {

    private SuggestFriendContract.View view;
    private SuggestRepository repository;

    public SuggestFriendPresenter(SuggestFriendContract.View view, SuggestRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void handlerGetSuggestList(String token) {
        repository.getSuggestFriend(token, 0, 20, new OnDataLoadedListener<List<Friend>>() {
            @Override
            public void onSuccess(List<Friend> data) {
                view.updateSuggestList(data);
            }

            @Override
            public void onFailure(Exception exception) {
                view.showMessage(Integer.parseInt(exception.getMessage()));
            }
        });
    }

    @Override
    public void handlerRequestFriend(String token, String userId, int position) {
        repository.getRequestFriend(token, userId, new OnDataLoadedListener<String>() {
            @Override
            public void onSuccess(String data) {
                view.showMessage(data, position);
            }

            @Override
            public void onFailure(Exception exception) {
                view.showError(exception.getMessage());
            }
        });
    }
}
