package com.example.facebookapp.ui.home.menu;

import com.example.facebookapp.R;
import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.repository.home.menu.MenuRepository;

public class MenuPresenter implements MenuContract.Presenter {

    private MenuContract.View view;
    private MenuRepository repository;

    public MenuPresenter(MenuContract.View view, MenuRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void handleLogout(String token) {
        if (token == null) {
            view.showError(R.string.error_token_null);
        } else {
            repository.logoutApi(token, new OnDataLoadedListener<String>() {
                @Override
                public void onSuccess(String data) {
                    view.successLogout();
                }

                @Override
                public void onFailure(Exception exception) {
                    view.showError(Integer.parseInt(exception.getMessage()));
                }
            });
        }
    }
}
