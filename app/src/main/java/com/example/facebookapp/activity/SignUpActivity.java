package com.example.facebookapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.facebookapp.R;
import com.example.facebookapp.model.AccountModel;
import com.example.facebookapp.network.ApiService;
import com.example.facebookapp.network.ResponseCode;
import com.example.facebookapp.network.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity.this";
    private static final String NOT_ENOUGH = "Not enough information";
    private static final String FORMAT_ERROR = "Format error";

    private ApiService mService;
    ProgressBar progressBar;

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

        editPhoneLayout = findViewById(R.id.edit_phone_signUp_textLayout);
        editPasswordLayout = findViewById(R.id.edit_password_signUp_textLayout);
        editConfirmLayout = findViewById(R.id.edit_confirm_password_textLayout);
        editPhone = findViewById(R.id.edit_phone_signUp);
        editPassword = findViewById(R.id.edit_password_signUp);
        editConfirmPassword = findViewById(R.id.edit_confirm_password);
        buttonSignUp = findViewById(R.id.button_signUp);
        mService = RetrofitClient.getInstance().create(ApiService.class);
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("SignUpPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String tempPhone = s.toString();
                if (tempPhone.charAt(0) != '0' || tempPhone.length() > 10)
                    editPhoneLayout.setError(FORMAT_ERROR);
                else
                    editPhoneLayout.setError(null);
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
                if (passwordEntering.length() > 10 || passwordEntering.length() < 6)
                    editPasswordLayout.setError(FORMAT_ERROR);
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
                    editConfirmLayout.setError("Confirm password is incorrect");
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

                if (validateAccount()) {

                    mService.signUp(phone, password, "23854")
                            .enqueue(new Callback<AccountModel>() {
                                @Override
                                public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                                    if (response.isSuccessful()) {
                                        String codeResponse = response.body().getCode();
                                        switch (codeResponse) {
                                            case ResponseCode.OK:
                                                Toast.makeText(getApplicationContext(), "Create account successful", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                                startActivity(intent);
                                                break;
                                            case ResponseCode.USER_EXISTED:
                                                //                                            editPhone.setError("Phone number or email is existed!!!");
                                                editPhoneLayout.setError("Phone number or email is existed!!!");
                                                break;
                                        }
                                    }

                                }

                                @Override
                                public void onFailure(Call<AccountModel> call, Throwable t) {
                                    Log.d(TAG, "onFailure: " + t.getMessage());
                                }
                            });
                }
            }
        });
    }
    private Boolean validateAccount() {
        if (phone.isEmpty()) {
            editPhoneLayout.setError(NOT_ENOUGH);
            return false;
        } else if (password.isEmpty()) {
            editPasswordLayout.setError(NOT_ENOUGH);
            return false;
        } else if (confirmPassword.isEmpty()) {
            editConfirmLayout.setError(NOT_ENOUGH);
            return false;
        }
        if (phone.charAt(0) != '0' || phone.length() > 10) {
            editPhoneLayout.setError(FORMAT_ERROR);
            return false;
        }

        editPhoneLayout.setError(null);
        editPasswordLayout.setError(null);
        editConfirmLayout.setError(null);
        return true;
    }
}