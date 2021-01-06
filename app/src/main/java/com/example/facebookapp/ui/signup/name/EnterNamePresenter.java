package com.example.facebookapp.ui.signup.name;

import com.example.facebookapp.R;

public class EnterNamePresenter implements EnterNameContract.Presenter {

    private EnterNameContract.View view;

    public EnterNamePresenter(EnterNameContract.View view) {
        this.view = view;
    }

    @Override
    public void checkInput(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            view.showErrorFirstName(R.string.empty);
            view.showErrorLastName(R.string.empty);
            view.nextEnterBirthday();
        }
        else {
            if (firstName == null)
                view.showErrorFirstName(R.string.error_null_input);
            if (lastName == null)
                view.showErrorLastName(R.string.error_null_input);
        }
    }

    @Override
    public void checkFirstName(String firstName) {
        if (firstName != null)
            view.showErrorFirstName(R.string.empty);
        else
            view.showErrorFirstName(R.string.error_null_input);
    }

    @Override
    public void checkLastName(String lastName) {
        if (lastName != null)
            view.showErrorLastName(R.string.empty);
        else
            view.showErrorLastName(R.string.error_null_input);
    }
}
