package com.example.facebookapp.data.model.friend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListUser {
    @SerializedName("list_users")
    @Expose
    private List<Friend> listUser;

    @SerializedName("total")
    @Expose
    private String total;

    @SerializedName("friends")
    @Expose
    private List<Friend> friends;

    public List<Friend> getListUser() {
        return listUser;
    }

    public String getTotal() {
        return total;
    }

    public List<Friend> getFriends() {
        return friends;
    }
}
