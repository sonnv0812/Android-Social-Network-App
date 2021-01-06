package com.example.facebookapp.data.model;

public class PostModel {
    private String avatar;
    private String nameAccount;
    private int timePost;
    private String textPost;
    private int numberLike;
    private boolean isLike;
    private boolean isComment;

    public PostModel(String avatar, String nameAccount, int timePost, String textPost, int numberLike, boolean isLike, boolean isComment) {
        this.avatar = avatar;
        this.nameAccount = nameAccount;
        this.timePost = timePost;
        this.textPost = textPost;
        this.numberLike = numberLike;
        this.isLike = isLike;
        this.isComment = isComment;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public boolean isComment() {
        return isComment;
    }

    public void setComment(boolean comment) {
        isComment = comment;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    public int getTimePost() {
        return timePost;
    }

    public void setTimePost(int timePost) {
        this.timePost = timePost;
    }

    public String getTextPost() {
        return textPost;
    }

    public void setTextPost(String textPost) {
        this.textPost = textPost;
    }

    public int getNumberLike() {
        return numberLike;
    }

    public void setNumberLike(int numberLike) {
        this.numberLike = numberLike;
    }

}
