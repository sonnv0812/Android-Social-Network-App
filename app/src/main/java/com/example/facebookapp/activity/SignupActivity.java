package com.example.facebookapp.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.facebookapp.R;
import com.example.facebookapp.model.AccountModel;
import com.example.facebookapp.network.ApiService;
import com.example.facebookapp.network.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity.class";
    private final String PHONE_ERROR = "Enter your phone number or your email";

    private ApiService mService;

    private String phone;
    private String password;
    private String confirmPassword;

    private TextInputLayout editPhone, editPassword, editConfirmPassword;
    private Button buttonSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editPhone = findViewById(R.id.edit_phone_signup);
        editPassword = findViewById(R.id.edit_password_signup);
        editConfirmPassword = findViewById(R.id.edit_confirm_password);
        buttonSignUp = findViewById(R.id.button_signup);
        mService = RetrofitClient.getInstance().create(ApiService.class);
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("SignUpPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        phone = editPhone.getEditText().getText().toString();
        password = editPassword.getEditText().getText().toString();
        confirmPassword = editConfirmPassword.getEditText().getText().toString();

        Objects.requireNonNull(editPhone.getEditText()).setText(phone);
        Objects.requireNonNull(editPassword.getEditText()).setText(password);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mService.signUp(phone, password, "23854")
                        .enqueue(new Callback<AccountModel>() {
                            @Override
                            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                                Log.d(TAG, "onResponse: " + response.body().toString());;
                            }

                            @Override
                            public void onFailure(Call<AccountModel> call, Throwable t) {
                                Log.d(TAG, "onFailure: " + t.getMessage());
                            }
                        });
            }
        });
    }
}