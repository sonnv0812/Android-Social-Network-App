package com.example.facebookapp.data.repository.home.newsfeed;

import android.util.Log;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.like.BaseLikeResponse;
import com.example.facebookapp.data.model.post.Author;
import com.example.facebookapp.data.model.post.BasePostResponse;
import com.example.facebookapp.data.model.post.Post;
import com.example.facebookapp.network.ApiService;
import com.example.facebookapp.network.ResponseCode;
import com.example.facebookapp.network.RetrofitPostClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFeedRepositoryImpl implements NewsFeedRepository {

    private final ApiService apiService = RetrofitPostClient.getInstance().create(ApiService.class);

    @Override
    public void loadNewsFeed(String token, String userId, String lastId, int index, int count, OnDataLoadedListener<List<Post>> callback) {
        List<Post> posts = new ArrayList<>();
        Log.v("RESPONSE", token);
        Log.v("RESPONSE", userId);
        Log.v("RESPONSE", lastId == null ? "NULL" : lastId);
        Log.v("RESPONSE", String.valueOf(index));
        Log.v("RESPONSE", String.valueOf(count));

        apiService.getListPost(token, userId, lastId, index, count).enqueue(new Callback<BasePostResponse>() {
            @Override
            public void onResponse(Call<BasePostResponse> call, Response<BasePostResponse> response) {
                switch (response.body().getCode()) {
                    case ResponseCode.OK:
                        int i = 0;
                        while (i < response.body().getData().getPost().size()) {
                            Author author = new Author(
                                    response.body().getData().getPost().get(i).getAuthor().getId(),
                                    response.body().getData().getPost().get(i).getAuthor().getName(),
                                    response.body().getData().getPost().get(i).getAuthor().getAvatar()
                            );
                            posts.add(new Post(
                                    response.body().getData().getPost().get(i).getId(),
                                    response.body().getData().getPost().get(i).getLike(),
                                    response.body().getData().getPost().get(i).getComment(),
                                    response.body().getData().getPost().get(i).getIsLiked(),
                                    response.body().getData().getPost().get(i).getCanEdit(),
                                    response.body().getData().getPost().get(i).getDescribed(),
                                    response.body().getData().getPost().get(i).getCreatedAt(),
                                    author,
                                    response.body().getData().getPost().get(i).getCanComment()
                            ));
                            i++;
                        }
                        callback.onSuccess(posts);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<BasePostResponse> call, Throwable t) {
                Log.v("RESPONSE", "failure");
                Log.v("RESPONSE", t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getLikePost(String token, String postId, int currentLike, OnDataLoadedListener<Integer> callback) {
        Log.v("LIKE", token);
        Log.v("LIKE", postId);
        apiService.likePost(token, postId).enqueue(new Callback<BaseLikeResponse>() {
            @Override
            public void onResponse(Call<BaseLikeResponse> call, Response<BaseLikeResponse> response) {
                switch (response.body().getCode()) {
                    case ResponseCode.OK:
                        callback.onSuccess(response.body().getData().getLike());
                        break;
                    case ResponseCode.UNKNOWN_ERROR:
                        callback.onSuccess(currentLike + 1);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<BaseLikeResponse> call, Throwable t) {

            }
        });
    }
}
