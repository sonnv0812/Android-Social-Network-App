package com.example.facebookapp.data.repository.friend.suggest;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.friend.Friend;

import java.util.List;

public interface SuggestRepository {
    void getSuggestFriend(String token, int index, int count, OnDataLoadedListener<List<Friend>> callback);

    void getRequestFriend(String token, String userId, OnDataLoadedListener<String> callback);
}
