package com.example.facebookapp.data.repository.home.friend;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.friend.Friend;

import java.util.List;

public interface FriendRepository {
    void getRequestFriend(String token, int index, int count, OnDataLoadedListener<List<Friend>> callback);

    void setAccept(String token, String userId, boolean isAccept, OnDataLoadedListener<String> callback);
}
