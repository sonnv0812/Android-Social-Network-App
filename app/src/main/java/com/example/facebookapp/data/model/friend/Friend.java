package com.example.facebookapp.data.model.friend;

public class Friend {
    private String id;
    private String username;
    private String avatar;
    private String sameFriend;
    private String created;

    public Friend(String id, String username, String avatar, String sameFriend, String created) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.sameFriend = sameFriend;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getSameFriend() {
        return sameFriend;
    }

    public String getCreated() {
        return created;
    }
}
