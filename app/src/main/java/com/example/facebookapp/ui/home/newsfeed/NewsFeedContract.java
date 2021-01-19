package com.example.facebookapp.ui.home.newsfeed;

import com.example.facebookapp.data.model.post.Post;

import java.util.List;

public interface NewsFeedContract {
    interface View {
        void initNewsFeed(List<Post> posts);

        void showMessage(int msgResId);

        void loadMoreNews(List<Post> posts);

        void updateLiked(int position, int like, int isLike);
    }

    interface Presenter {
        void requestGetPost(String token, String userId, String lastId, int index, int count);

        void likePost(String token, String postId, int position, int currentLike);
    }
}
