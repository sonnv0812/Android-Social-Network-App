package com.example.facebookapp.ui.home.newfeed;

import com.example.facebookapp.data.model.post.Post;

import java.util.List;

public interface NewFeedContract {
    interface View {
        void updateNewFeed(List<Post> posts);

        void showMessage(int msgResId);
    }

    interface Presenter {
        void requestGetPost(String token, String userId, String lastId, int index, int count);
    }
}
