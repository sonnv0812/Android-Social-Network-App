package com.example.facebookapp.ui.signup.password;

import com.example.facebookapp.R;

public class EnterPasswordPresenter implements EnterPasswordContract.Presenter {

    private EnterPasswordContract.View view;

    public EnterPasswordPresenter(EnterPasswordContract.View view) {
        this.view = view;
    }

    @Override
    public void checkValidatePassword(String password) {
        if (password.length() > 10 || password.length() < 6)
            view.showPasswordError(R.string.error_format_input);
        else
            view.showPasswordError(R.string.empty);
    }

    @Override
    public void checkConfirmPassword(String password, String confirmPassword) {
        if (confirmPassword.equals(password)) {
            view.showConfirmPassError(R.string.empty);
            view.nextConfirm();
        } else
            view.showConfirmPassError(R.string.error_confirm_password);
    }
}
