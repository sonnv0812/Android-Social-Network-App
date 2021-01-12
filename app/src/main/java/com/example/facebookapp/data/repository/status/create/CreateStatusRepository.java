package com.example.facebookapp.data.repository.status.create;

import com.example.facebookapp.data.base.OnDataLoadedListener;

import java.util.List;

public interface CreateStatusRepository {
    void getApiAddPost(String token,
                       List<String> image,
                       String video,
                       String described,
                       String status,
                       OnDataLoadedListener<String> callback);
}
