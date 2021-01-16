package com.example.facebookapp.ui.home.newfeed;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.post.Post;
import com.example.facebookapp.data.repository.home.newfeed.NewFeedRepository;

import java.util.List;

public class NewFeedPresenter implements NewFeedContract.Presenter {

    private NewFeedContract.View view;
    private NewFeedRepository repository;

    public NewFeedPresenter(NewFeedContract.View view, NewFeedRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void requestGetPost(String token, String userId, String lastId, int index, int count) {
        repository.getListPost(token, userId, lastId, index, count, new OnDataLoadedListener<List<Post>>() {
            @Override
            public void onSuccess(List<Post> data) {
                view.updateNewFeed(data);
            }

            @Override
            public void onFailure(Exception exception) {
                view.showMessage(Integer.parseInt(exception.getMessage()));
            }
        });
    }
}
