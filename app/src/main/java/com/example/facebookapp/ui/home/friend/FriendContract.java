package com.example.facebookapp.ui.home.friend;

import com.example.facebookapp.data.model.friend.Friend;

import java.util.List;

public interface FriendContract {
    interface View {
        void updateData(List<Friend> friendRequestList);

        void showMessage(int msgResId);
    }

    interface Presenter {
        void handleRequestFriend(String token);

        void handleGetUserFriend();

        void handleAcceptFriend(String userId);

        void handleDeleteRequest(String userId);
    }
}
