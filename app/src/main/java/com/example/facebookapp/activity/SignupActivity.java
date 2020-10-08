package com.example.facebookapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.facebookapp.R;

public class SignupActivity extends AppCompatActivity {

    private final String PHONE_ERROR = "Enter your phone number or your email";

    private String phone;
    private String password;
    private String confirmPassword;

    private EditText editPhone;
    private EditText editPassword;
    private EditText editConfirmPassword;
    private Button buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editPhone = findViewById(R.id.edit_phone_signup);
        editPassword = findViewById(R.id.edit_password_signup);
        editConfirmPassword = findViewById(R.id.edit_confirm_password);
        buttonSignup = findViewById(R.id.button_signup);
    }

    @Override
    protected void onResume() {
        super.onResume();

        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                phone = s.toString();
                if (phone.length() != 10 || phone.charAt(0) != '0') {
                    phone = null;
                    editPhone.setError(PHONE_ERROR);
                }
            }
        });

        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String tempPassword = s.toString();
                if (tempPassword.length() < 6)
                    editPassword.setError("Too short");
                if (tempPassword.length() > 10)
                    editPassword.setError("Too long");
            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
                if (password.equals(phone)) {
                    password = null;
                    editPassword.setError("Is not duplicate");
                }
            }
        });

        editConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                confirmPassword = s.toString();
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!password.equals(confirmPassword)) {
                    editPassword.setText("");
                    editConfirmPassword.setText("");
                    editConfirmPassword.setError("Retype incorrect");
                    buttonSignup.setEnabled(false);
                }
                if (editPhone.getError() != null || editPassword.getError() != null) {
                    buttonSignup.setEnabled(false);
                    Toast.makeText(getApplicationContext(), "Retype your account", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}