package com.example.facebookapp.ui.friend.all;

import com.example.facebookapp.data.model.friend.Friend;

import java.util.List;

public interface AllFriendContract {
    interface View {
        void updateFriendData(List<Friend> friendList);

        void showMessage(int msgResId);
    }

    interface Presenter {
        void handleGetUserFriend(String token, String userId);
    }
}
