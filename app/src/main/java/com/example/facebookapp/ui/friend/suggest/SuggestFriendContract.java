package com.example.facebookapp.ui.friend.suggest;

import com.example.facebookapp.data.model.friend.Friend;

import java.util.List;

public interface SuggestFriendContract {
    interface View {
        void updateSuggestList(List<Friend> friendList);

        void showMessage(int msgResId);

        void showMessage(String msg, int position);

        void showError(String msg);
    }

    interface Presenter {
        void handlerGetSuggestList(String token);

        void handlerRequestFriend(String token, String userId, int position);
    }
}
