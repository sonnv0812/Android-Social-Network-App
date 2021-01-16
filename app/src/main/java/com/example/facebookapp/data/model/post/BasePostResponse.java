package com.example.facebookapp.data.model.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasePostResponse {
    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private DataPost data;

    public BasePostResponse(String code, String message, DataPost data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public DataPost getData() {
        return data;
    }
}
