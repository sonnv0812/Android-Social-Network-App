package com.example.facebookapp.data.model;

import com.example.facebookapp.data.model.account.AccountModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("code")
    @Expose
    protected String code;

    @SerializedName("data")
    @Expose
    private AccountModel accountModel;

    @SerializedName("token")
    @Expose
    protected String token;

//    @SerializedName("data")
//    @Expose
//    private ListUser requestFriend;

    public String getCode() {
        return code;
    }

    public String getToken() {
        return token;
    }

    public AccountModel getAccountModel() {
        return accountModel;
    }

//    public ListUser getRequestFriend() {
//        return requestFriend;
//    }
}
