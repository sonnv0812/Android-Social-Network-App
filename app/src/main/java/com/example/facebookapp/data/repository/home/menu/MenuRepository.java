package com.example.facebookapp.data.repository.home.menu;

import com.example.facebookapp.data.base.OnDataLoadedListener;

public interface MenuRepository {
    void logoutApi(String token, OnDataLoadedListener<String> callback);
}
