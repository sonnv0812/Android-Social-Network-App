package com.example.facebookapp.data.model.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.Date;
import java.util.List;

public class Post {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("image")
    @Expose
    private List<File> image;

    @SerializedName("video")
    @Expose
    private List<File> video;

    @SerializedName("like")
    @Expose
    private int like;

    @SerializedName("comment")
    @Expose
    private int comment;

    @SerializedName("is_liked")
    @Expose
    private int isLiked;

    @SerializedName("can_edit")
    @Expose
    private int canEdit;

    @SerializedName("described")
    @Expose
    private String described;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("created")
    @Expose
    private Date createdAt;

    @SerializedName("author")
    @Expose
    private Author author;

    @SerializedName("can_comment")
    @Expose
    private int canComment;

    public String getId() {
        return id;
    }

    public List<File> getImage() {
        return image;
    }

    public List<File> getVideo() {
        return video;
    }

    public int getLike() {
        return like;
    }

    public int getComment() {
        return comment;
    }

    public int getIsLiked() {
        return isLiked;
    }

    public int getCanEdit() {
        return canEdit;
    }

    public String getDescribed() {
        return described;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Author getAuthor() {
        return author;
    }

    public int getCanComment() {
        return canComment;
    }

    public Post(String id, int like, int comment, int isLiked, int canEdit, String described, Date createdAt, Author author, int canComment) {
        this.id = id;
        this.like = like;
        this.comment = comment;
        this.isLiked = isLiked;
        this.canEdit = canEdit;
        this.described = described;
        this.createdAt = createdAt;
        this.author = author;
        this.canComment = canComment;
    }
}
