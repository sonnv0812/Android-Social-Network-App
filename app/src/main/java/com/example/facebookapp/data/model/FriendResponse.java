package com.example.facebookapp.data.model;

import com.example.facebookapp.data.model.friend.ListUser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendResponse {
    @SerializedName("code")
    @Expose
    protected String code;

    @SerializedName("data")
    @Expose
    private ListUser requestFriend;

    public String getCode() {
        return code;
    }

    public ListUser getRequestFriend() {
        return requestFriend;
    }
}
