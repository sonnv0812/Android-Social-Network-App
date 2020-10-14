package com.example.facebookapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.facebookapp.R;
import com.example.facebookapp.config.CheckValidate;
import com.example.facebookapp.model.AccountModel;
import com.example.facebookapp.network.ApiService;
import com.example.facebookapp.network.NetworkChangedConnection;
import com.example.facebookapp.network.ResponseCode;
import com.example.facebookapp.network.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity.this";

    private NetworkChangedConnection networkChangedConnection;
    private Button btnLogin, btnCreateAccount;
    private TextInputLayout editPhoneLayout, editPasswordLayout;
    private EditText editPhone, editPassword;
    private String phone = "", password = "";
    private ApiService apiService;
    private CheckValidate checkValidate = new CheckValidate();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnLogin = findViewById(R.id.button_login);
        btnCreateAccount = findViewById(R.id.button_create_account);
        editPhoneLayout = findViewById(R.id.edit_phone_layout);
        editPasswordLayout = findViewById(R.id.edit_password_layout);
        editPhone = findViewById(R.id.edit_phone);
        editPassword = findViewById(R.id.edit_password);
        apiService = RetrofitClient.getInstance().create(ApiService.class);
        networkChangedConnection = new NetworkChangedConnection();
        progressBar = findViewById(R.id.progressBar_login);
        progressBar.setVisibility(View.GONE);
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
                String passwordTemp = s.toString();
                if (!checkValidate.isValidPassword(passwordTemp, phone))
                    editPasswordLayout.setError(getApplicationContext().getString(R.string.error_input_account));
                else
                    editPasswordLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
            }
        });

        btnLogin.setOnClickListener(this);
        btnCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_login:
                if (checkValidate.validateAccount(phone, password)) {
                    progressBar.setVisibility(View.VISIBLE);
//                    if (!networkChangedConnection.isOnline()) {
//                        Toast.makeText(getApplicationContext(), R.string.error_no_network, Toast.LENGTH_LONG).show();
//                        progressBar.setVisibility(View.GONE);
//                    }
                    apiService.login(phone, password, "123456").enqueue(new Callback<AccountModel>() {
                        @Override
                        public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                            progressBar.setVisibility(View.GONE);
                            if (response.isSuccessful()) {
                                String codeResponse = response.body().getCode();
                                switch (codeResponse) {
                                    case ResponseCode.OK:
                                        Toast.makeText(getApplicationContext(), R.string.notify_login_successful, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        break;
                                    case ResponseCode.USER_IS_NOT_INVALID:
                                        editPhoneLayout.setError(getApplicationContext().getString(R.string.error_login_invalid));
                                        break;
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<AccountModel> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), R.string.error_no_network, Toast.LENGTH_LONG).show();
                        }
                    });
                }
                break;

            case R.id.button_create_account:
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

}
