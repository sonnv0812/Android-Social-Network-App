package com.example.facebookapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.facebookapp.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private Button btnCreateAccount;
    private TextInputLayout editPhone, editPassword;

    private String phoneEmail;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.button_login);
        btnCreateAccount = findViewById(R.id.button_create_account);
        editPhone = findViewById(R.id.edit_phone);
        editPassword = findViewById(R.id.edit_password);


    }

    @Override
    protected void onResume() {
        super.onResume();

        btnLogin.setOnClickListener((View.OnClickListener) this);
        btnCreateAccount.setOnClickListener((View.OnClickListener) this);

    }

    private void confirmInput() {
        if(!phoneEmail.equals("1")) {
            return;
        }
        else if(!password.equals("1")) {
            return;
        }
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_login:
                confirmInput();
                break;
            case R.id.button_create_account:
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                break;
        }
    }
}
