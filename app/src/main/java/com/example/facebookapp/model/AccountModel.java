package com.example.facebookapp.model;

public class AccountModel {
    private String phoneOrEmail;
    private String password;

    public AccountModel(String phoneOrEmail, String password) {
        this.phoneOrEmail = phoneOrEmail;
        this.password = password;
    }

    public String getPhoneOrEmail() {
        return phoneOrEmail;
    }

    public String getPassword() {
        return password;
    }
}
