package com.example.facebookapp.ui.signup.birthday;

import java.util.Date;

public interface EnterBirthdayContract {
    interface View {
        void showMsg(int msgResId);

        void confirmDate(Date date);
    }

    interface Presenter {
        void handleConvertDate(int dayOfMonth, int monthOfYear, int year);
    }
}
