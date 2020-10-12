package com.example.facebookapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class BaseResponse {
    @SerializedName("code")
    @Expose
    protected String code;
}
