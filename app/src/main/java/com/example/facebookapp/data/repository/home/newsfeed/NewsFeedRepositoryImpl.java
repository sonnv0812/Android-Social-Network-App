package com.example.facebookapp.data.repository.home.newsfeed;

import android.util.Log;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.post.BasePostResponse;
import com.example.facebookapp.data.model.post.Post;
import com.example.facebookapp.network.ApiService;
import com.example.facebookapp.network.ResponseCode;
import com.example.facebookapp.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFeedRepositoryImpl implements NewsFeedRepository {

    private ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

    @Override
    public void getListPost(String token, String userId, String lastId, int index, int count, OnDataLoadedListener<List<Post>> callback) {
        List<Post> posts = new ArrayList<>();
        apiService.getListPosts(token, userId, lastId, index, count).enqueue(new Callback<BasePostResponse>() {
            @Override
            public void onResponse(Call<BasePostResponse> call, Response<BasePostResponse> response) {
                switch (response.body().getCode()) {
                    case ResponseCode.OK:
                        int i = 0;
                        while (i < response.body().getData().getPost().size()) {
                            posts.add(new Post(
                                    response.body().getData().getPost().get(i).getId(),
                                    response.body().getData().getPost().get(i).getLike(),
                                    response.body().getData().getPost().get(i).getComment(),
                                    response.body().getData().getPost().get(i).getIsLiked(),
                                    response.body().getData().getPost().get(i).getCanEdit(),
                                    response.body().getData().getPost().get(i).getDescribed(),
                                    response.body().getData().getPost().get(i).getCreatedAt(),
                                    response.body().getData().getPost().get(i).getAuthor(),
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
                Log.v("RESPONSE", t.getMessage());
//                callback.onFailure(new Exception(t.getMessage()));
            }
        });
    }
}
