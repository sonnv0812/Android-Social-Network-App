package com.example.facebookapp.data.model.friend;

public class Friend {
    private String id;
    private String username;
    private String avatar;
    private int sameFriend;
    private String created;

    public Friend(String id, String username, String avatar, int sameFriend, String created) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.sameFriend = sameFriend;
        this.created = created;
    }

    public Friend(String id, String username, String avatar, int sameFriend) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.sameFriend = sameFriend;
    }

    public Friend(String id, String avatar, int sameFriend) {
        this.id = id;
        this.avatar = avatar;
        this.sameFriend = sameFriend;
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

    public int getSameFriend() {
        return sameFriend;
    }

    public String getCreated() {
        return created;
    }
}
