package com.example.facebookapp.ui.signup.phone;

import com.example.facebookapp.R;
import com.example.facebookapp.config.CheckValidate;

public class EnterPhonePresenter implements EnterPhoneContract.Presenter {

    private EnterPhoneContract.View view;

    public EnterPhonePresenter(EnterPhoneContract.View view) {
        this.view = view;
    }

    @Override
    public void checkValidatePhone(String phone) {
        CheckValidate validate = new CheckValidate();
        if (validate.isValidPhone(phone)) {
            view.showError(R.string.empty);
        } else {
            view.showError(R.string.error_format_input);
        }
    }
}
