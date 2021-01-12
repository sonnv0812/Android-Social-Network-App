package com.example.facebookapp.data.model.friend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestFriend {
    @SerializedName("request")
    @Expose
    private Friend request;

    @SerializedName("total")
    @Expose
    private String total;

    public RequestFriend(Friend request, String total) {
        this.request = request;
        this.total = total;
    }

    public Friend getRequest() {
        return request;
    }

    public String getTotal() {
        return total;
    }
}
