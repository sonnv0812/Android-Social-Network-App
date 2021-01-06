package com.example.facebookapp.ui.signup.mail;

import com.example.facebookapp.R;
import com.example.facebookapp.config.CheckValidate;

public class EnterMailPresenter implements EnterMailContract.Presenter {

    private EnterMailContract.View view;

    public EnterMailPresenter(EnterMailContract.View view) {
        this.view = view;
    }


    @Override
    public void checkValidateMail(String mail) {
        CheckValidate validate = new CheckValidate();
        if (validate.isValidMail(mail)) {
            view.showError(R.string.empty);
        } else {
            view.showError(R.string.error_format_input);
        }
    }
}
