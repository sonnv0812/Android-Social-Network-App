package com.example.facebookapp.data.repository.home.newsfeed;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.post.Post;

import java.util.List;

public interface NewsFeedRepository {
    void loadNewsFeed(String token, String userId, String lastId, int index, int count, OnDataLoadedListener<List<Post>> callback);

    void getLikePost(String token, String postId, int currentLike, OnDataLoadedListener<Integer> callback);
}
