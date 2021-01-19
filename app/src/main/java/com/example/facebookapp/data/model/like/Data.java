package com.example.facebookapp.data.model.like;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("like")
    @Expose
    private int like;

    public int getLike() {
        return like;
    }
}
