package com.example.facebookapp.data.repository.createstatus;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.BaseResponse;

import java.util.List;

public interface CreateStatusRepository {
    void getApiAddPost(String token,
                       List<String> image,
                       String video,
                       String described,
                       String status,
                       OnDataLoadedListener<String> callback);
}
