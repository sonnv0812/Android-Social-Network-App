package com.example.facebookapp.data.model.friend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestedFriend {
    @SerializedName("requested_friends")
    @Expose
    private String requestedFriend;

    public String getRequestedFriend() {
        return requestedFriend;
    }
}
