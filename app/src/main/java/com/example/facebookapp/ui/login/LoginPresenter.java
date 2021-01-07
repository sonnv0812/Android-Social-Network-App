package com.example.facebookapp.ui.login;

import com.example.facebookapp.R;
import com.example.facebookapp.config.CheckValidate;
import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.model.AccountModel;
import com.example.facebookapp.data.repository.login.LoginRepository;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private LoginRepository repository;

    public LoginPresenter(LoginContract.View view, LoginRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void handleLogin(String phone, String password, String uuid) {
        CheckValidate validate = new CheckValidate();
        if (validate.validateAccount(phone, password)) {
            repository.loginAction(phone, password, uuid, new OnDataLoadedListener<AccountModel>() {
                @Override
                public void onSuccess(AccountModel data) {
                    view.nextHome(data);
                }

                @Override
                public void onFailure(Exception exception) {
                    view.showError(Integer.parseInt(exception.getMessage()));
                }
            });
        } else {
            view.showError(R.string.error_format_input);
        }
    }

    @Override
    public void checkInputPhoneEmail(String phoneEmail) {
        CheckValidate validate = new CheckValidate();
        if (validate.isValidPhone(phoneEmail) || validate.isValidMail(phoneEmail))
            view.showPhoneError(R.string.empty);
        else
            view.showPhoneError(R.string.error_format_input);
    }

    @Override
    public void checkInputPassword(String password, String phone) {
        CheckValidate validate = new CheckValidate();
        if (validate.isValidPassword(password, phone))
            view.showPasswordError(R.string.empty);
        else
            view.showPasswordError(R.string.error_format_input);
    }
}
