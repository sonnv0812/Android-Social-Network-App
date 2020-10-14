package com.example.facebookapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.facebookapp.R;
import com.example.facebookapp.config.CheckValidate;
import com.example.facebookapp.model.AccountModel;
import com.example.facebookapp.network.ApiService;
import com.example.facebookapp.network.ResponseCode;
import com.example.facebookapp.network.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity.this";

    private ApiService mService;
    private ProgressBar progressBar;
    private CheckValidate checkValidate = new CheckValidate();

    private String phone = "";
    private String password = "";
    private String confirmPassword = "";
    private EditText editPhone, editPassword, editConfirmPassword;
    private TextInputLayout editPhoneLayout, editPasswordLayout, editConfirmLayout;
    private Button buttonSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editPhoneLayout = findViewById(R.id.edit_phone_signUp_textLayout);
        editPasswordLayout = findViewById(R.id.edit_password_signUp_textLayout);
        editConfirmLayout = findViewById(R.id.edit_confirm_password_textLayout);
        editPhone = findViewById(R.id.edit_phone_signUp);
        editPassword = findViewById(R.id.edit_password_signUp);
        editConfirmPassword = findViewById(R.id.edit_confirm_password);
        buttonSignUp = findViewById(R.id.button_signUp);
        progressBar = findViewById(R.id.progressBar_signUp);

        progressBar.setVisibility(View.GONE);

        mService = RetrofitClient.getInstance().create(ApiService.class);

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
                String temp = s.toString();
                if (checkValidate.isValidMobile(temp) || checkValidate.isValidMail(temp))
                    editPhoneLayout.setError(null);
                else
                    editPhoneLayout.setError(getApplicationContext().getString(R.string.error_input_account));
            }

            @Override
            public void afterTextChanged(Editable s) {
                phone = s.toString();
            }
        });

        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String passwordEntering = s.toString();
                if (!checkValidate.isValidPassword(passwordEntering, phone))
                    editPasswordLayout.setError(getApplicationContext().getString(R.string.error_input_account));
                else
                    editPasswordLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
            }
        });

        editConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String tempConfirmPass = s.toString();
                if (!tempConfirmPass.equals(password))
                    editConfirmLayout.setError(getApplicationContext().getString(R.string.error_confirm_password));
                else
                    editConfirmLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                confirmPassword = s.toString();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidate.validateAccount(phone, password)) {
                    progressBar.setVisibility(View.VISIBLE);
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    mService.signUp(phone, password, "111444").enqueue(new Callback<AccountModel>() {
                        @Override
                        public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                            progressBar.setVisibility(View.GONE);
                            if (response.isSuccessful()) {
                                String codeResponse = response.body().getCode();
                                Log.d(TAG, "onResponse: " + codeResponse);
                                switch (codeResponse) {
                                    case ResponseCode.OK:
                                        Toast.makeText(getApplicationContext(), R.string.notify_create_account_successful, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        break;
                                    case ResponseCode.USER_EXISTED:
                                        editPhoneLayout.setError(getApplicationContext().getString(R.string.error_phone_existed));
                                        break;
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<AccountModel> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            Log.d(TAG, "onFailure: " + t.getMessage());
                        }
                    });
                }
            }
        });
    }
}