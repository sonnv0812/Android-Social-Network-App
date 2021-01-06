package com.example.facebookapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountModel extends BaseResponse {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("avatar")
    @Expose
    private String avatarLink;
    @SerializedName("token")
    @Expose
    private String token;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public String getToken() {
        return token;
    }

    public AccountModel(String id, String username, String avatarLink, String token) {
        this.id = id;
        this.username = username;
        this.avatarLink = avatarLink;
        this.token = token;
    }
}
