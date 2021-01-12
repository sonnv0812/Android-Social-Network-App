package com.example.facebookapp.data.repository.friend.all;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.friend.Friend;

import java.util.List;

public interface AllFriendRepository {
    void getUserFriend(String token, String userId, int index, int count, OnDataLoadedListener<List<Friend>> callback);
}
