package com.example.facebookapp.ui.signup.name;

public interface EnterNameContract {
    interface View {
        void showErrorFirstName(int msgResId);

        void showErrorLastName(int msgResId);

        void nextEnterBirthday();
    }

    interface Presenter {
        void checkInput(String firstName, String lastName);

        void checkFirstName(String firstName);

        void checkLastName(String lastName);
    }
}
