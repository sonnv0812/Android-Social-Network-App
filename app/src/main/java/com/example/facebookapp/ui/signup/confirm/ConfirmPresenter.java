package com.example.facebookapp.ui.signup.confirm;

import com.example.facebookapp.R;
import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.repository.singup.confirm.ConfirmRepository;

public class ConfirmPresenter implements ConfirmContract.Presenter {

    private ConfirmContract.View view;
    private ConfirmRepository repository;

    public ConfirmPresenter(ConfirmContract.View view, ConfirmRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void handleSignUp(String confirmCode, String phone, String mail, String password, String uuid) {
        String user;
        if (confirmCode == null || !confirmCode.equals("12345")) {
            view.showError(R.string.error_not_correct_confirmCode_signUp);
        } else {
            user = phone != null ? phone : mail;
            repository.getSignUp(user, password, uuid, new OnDataLoadedListener<String>() {
                @Override
                public void onSuccess(String data) {
                    view.nextLogin();
                }

                @Override
                public void onFailure(Exception exception) {
                    view.showError(Integer.parseInt(exception.getMessage()));
                }
            });
        }
    }

    @Override
    public void handleCreateUI(String phone, String mail) {
        view.updateCreateUI(phone != null);
    }
}
