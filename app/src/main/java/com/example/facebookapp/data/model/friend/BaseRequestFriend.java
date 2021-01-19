package com.example.facebookapp.data.model.friend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseRequestFriend {
    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private RequestedFriend requestedFriend;

    public String getCode() {
        return code;
    }

    public RequestedFriend getRequestedFriend() {
        return requestedFriend;
    }

    public String getMessage() {
        return message;
    }
}
