package com.example.facebookapp.ui.home.friend;

import com.example.facebookapp.data.model.friend.Friend;

import java.util.List;

public interface FriendContract {
    interface View {
        void updateData(List<Friend> friendRequestList);

        void showMessage(int msgResId);

        void updateUIAfterAccept(String userId, int position);
    }

    interface Presenter {
        void handleRequestFriend(String token);

        void handleAcceptFriend(String token, String userId, boolean isAccept, int position);
    }
}
