package com.example.facebookapp.ui.friend.suggest;

import com.example.facebookapp.data.model.friend.Friend;

import java.util.List;

public interface SuggestFriendContract {
    interface View {
        void updateSuggestList(List<Friend> friendList);

        void showMessage(int msgResId);
    }

    interface Presenter {
        void handlerGetSuggestList(String token);
    }
}
