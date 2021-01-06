package com.example.facebookapp.config;

import java.util.regex.Pattern;

public class CheckValidate {
    public boolean isValidMail(String email) {
        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(EMAIL_STRING).matcher(email).matches();
    }

    public boolean isValidPhone(String phone) {
        String PHONE_STRING = "(0)+([0-9]{9})\\b";
        return Pattern.compile(PHONE_STRING).matcher(phone).matches();
    }

    public boolean validateAccount(String phone, String password) {
        if (phone == null || password == null)
             return false;
        if (!phone.isEmpty() && !password.isEmpty())
            return true;
        return false;
    }

    public boolean isValidPassword(String password, String phone) {
        if(password.length() > 10 || password.length() < 6 || password.equals(phone))
            return false;
        return true;
    }
}
