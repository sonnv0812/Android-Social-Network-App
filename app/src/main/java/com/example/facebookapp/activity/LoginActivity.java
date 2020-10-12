package com.example.facebookapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.facebookapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private Button btnCreateAccount;
    private TextInputLayout editPhone, editPassword;

    private String phone;
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onResume() {
        super.onResume();

        btnLogin.setOnClickListener((View.OnClickListener) this);
        btnCreateAccount.setOnClickListener((View.OnClickListener) this);

        SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        phone = sharedPreferences.getString("phone", "");
        password = sharedPreferences.getString("password", "");
        Objects.requireNonNull(editPhone.getEditText()).setText(phone);
        Objects.requireNonNull(editPassword.getEditText()).setText(password);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void confirmInput() {
        phone = Objects.requireNonNull(editPhone.getEditText()).getText().toString().trim();
        password = Objects.requireNonNull(editPassword.getEditText()).getText().toString().trim();

        if(!phone.equals("1")) {
            return;
        }
        else if(!password.equals("1")) {
            return;
        }
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
