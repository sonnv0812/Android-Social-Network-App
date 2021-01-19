package com.example.facebookapp.data.repository.post.create;

import com.example.facebookapp.data.base.OnDataLoadedListener;

import java.io.File;
import java.util.List;

public interface CreateStatusRepository {
    void getApiAddPost(String token,
                       String described,
                       String status,
                       List<File> image,
                       File video,
                       OnDataLoadedListener<String> callback);
}
