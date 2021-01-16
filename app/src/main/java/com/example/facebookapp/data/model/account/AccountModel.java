package com.example.facebookapp.data.model.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountModel extends BaseUserResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("avatar")
    @Expose
    private String avatarLink;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public AccountModel(String id, String username, String avatarLink, String token) {
        this.id = id;
        this.username = username;
        this.avatarLink = avatarLink;
        super.token = token;
    }
}
