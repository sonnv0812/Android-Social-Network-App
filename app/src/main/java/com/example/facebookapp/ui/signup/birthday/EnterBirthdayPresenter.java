package com.example.facebookapp.ui.signup.birthday;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EnterBirthdayPresenter implements EnterBirthdayContract.Presenter {

    private EnterBirthdayContract.View view;

    public EnterBirthdayPresenter(EnterBirthdayContract.View view) {
        this.view = view;
    }

    @Override
    public void handleConvertDate(int dayOfMonth, int monthOfYear, int year) {
        String month = monthOfYear/10 > 0 ? String.valueOf(monthOfYear) : "0" + String.valueOf(monthOfYear);
        String day = dayOfMonth/10 > 0 ? String.valueOf(dayOfMonth) : "0" + String.valueOf(dayOfMonth);
        String dateOfBirth = month + "/" + day + "/" + year;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date birthday;
        try {
            birthday = dateFormat.parse(dateOfBirth);
            view.confirmDate(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
