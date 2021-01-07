package com.example.facebookapp.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.facebookapp.R;
import com.example.facebookapp.data.repository.login.LoginRepository;
import com.example.facebookapp.data.repository.login.LoginRepositoryImpl;
import com.example.facebookapp.ui.home.activity.HomeActivity;
import com.example.facebookapp.ui.signup.activity.SignUpActivity;
import com.example.facebookapp.data.model.AccountModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.UUID;

public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener, LoginContract.View {

    private static final String TAG = "LoginActivity.this";

    private LoginContract.Presenter presenter;
    private Button btnLogin, btnCreateAccount;
    private TextInputLayout editPhoneLayout, editPasswordLayout;
    private EditText editPhone, editPassword;
    private String phone, password;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.button_login);
        btnCreateAccount = findViewById(R.id.button_create_account);
        editPhoneLayout = findViewById(R.id.edit_phone_layout);
        editPasswordLayout = findViewById(R.id.edit_password_layout);
        editPhone = findViewById(R.id.edit_phone);
        editPassword = findViewById(R.id.edit_password);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Đợi chút");

        initPresenter();
    }

    private void initPresenter() {
        LoginRepository repository = new LoginRepositoryImpl();
        presenter = new LoginPresenter(this, repository);
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
                phone = s.toString();
                presenter.checkInputPhoneEmail(phone);
            }

            @Override
            public void afterTextChanged(Editable s) {
                phone = s.toString();
                presenter.checkInputPhoneEmail(phone);
            }
        });
        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = s.toString();
                presenter.checkInputPassword(password, phone);
            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
                presenter.checkInputPassword(password, phone);
            }
        });

        btnLogin.setOnClickListener(this);
        btnCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_create_account:
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.button_login:
                String uuid = UUID.randomUUID().toString().replace("-", "");
                dialog.show();
                presenter.handleLogin(phone, password, uuid);
                break;
        }
    }

    @Override
    public void showPhoneError(int msgResId) {
        editPhoneLayout.setError(getString(msgResId));
    }

    @Override
    public void showPasswordError(int msgResId) {
        editPasswordLayout.setError(getString(msgResId));
    }

    @Override
    public void showError(int msgResId) {
        dialog.dismiss();
        Toast.makeText(this, getString(msgResId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void nextHome(AccountModel account) {
        dialog.dismiss();
        Toast.makeText(this, getString(R.string.notify_login_successful), Toast.LENGTH_LONG).show();

        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        Bundle responseData = new Bundle();
        responseData.putString(getString(R.string.key_id), account.getId());
        responseData.putString(getString(R.string.key_token), account.getToken());
        responseData.putString(getString(R.string.key_avatar), account.getAvatarLink());
        intent.putExtras(responseData);

        startActivity(intent, responseData);

    }
}
