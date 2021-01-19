package com.example.facebookapp.ui.home.newsfeed;

import android.util.Log;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.post.Post;
import com.example.facebookapp.data.repository.home.newsfeed.NewsFeedRepository;

import java.util.List;

public class NewsFeedPresenter implements NewsFeedContract.Presenter {

    private NewsFeedContract.View view;
    private NewsFeedRepository repository;

    public NewsFeedPresenter(NewsFeedContract.View view, NewsFeedRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void requestGetPost(String token, String userId, String lastId, int index, int count) {
        repository.loadNewsFeed(token, userId, lastId, index, count, new OnDataLoadedListener<List<Post>>() {
            @Override
            public void onSuccess(List<Post> data) {
                if (index != 0)
                    view.initNewsFeed(data);
                else
                    view.loadMoreNews(data);
            }

            @Override
            public void onFailure(Exception exception) {
                view.showMessage(Integer.parseInt(exception.getMessage()));
            }
        });
    }

    @Override
    public void likePost(String token, String postId, int position, int currentLike) {
        repository.getLikePost(token, postId, currentLike, new OnDataLoadedListener<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                Log.v("LIKE", String.valueOf(data));
                if (data > currentLike)
                    view.updateLiked(position, data, 1);
                else
                    view.updateLiked(position, data, 0);
            }

            @Override
            public void onFailure(Exception exception) {

            }
        });
    }
}
