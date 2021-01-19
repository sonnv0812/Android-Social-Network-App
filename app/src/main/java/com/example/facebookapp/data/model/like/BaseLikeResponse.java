package com.example.facebookapp.data.model.like;

import com.example.facebookapp.data.model.post.DataPost;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseLikeResponse {
    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private Data data;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }
}
