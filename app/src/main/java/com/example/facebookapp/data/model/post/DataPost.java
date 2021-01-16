package com.example.facebookapp.data.model.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataPost {
    @SerializedName("post")
    @Expose
    private List<Post> post = null;

    public List<Post> getPost() {
        return post;
    }
}
