package com.example.facebookapp.data.repository.home.newfeed;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.post.Post;

import java.util.List;

public interface NewFeedRepository {
    void getListPost(String token, String userId, String lastId, int index, int count, OnDataLoadedListener<List<Post>> callback);
}
