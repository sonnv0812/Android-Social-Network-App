package com.example.facebookapp.model;

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
    private List<String> blockId;
    private String password;
    private String phoneNumber;
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public String getToken() {
        return token;
    }

    public List<String> getBlockId() {
        return blockId;
    }
}
