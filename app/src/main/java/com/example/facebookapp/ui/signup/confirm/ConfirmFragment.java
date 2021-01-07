package com.example.facebookapp.ui.signup.confirm;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.facebookapp.R;
import com.example.facebookapp.data.repository.singup.confirm.ConfirmRepository;
import com.example.facebookapp.data.repository.singup.confirm.ConfirmRepositoryImpl;
import com.example.facebookapp.ui.login.LoginActivity;
import com.example.facebookapp.ui.signup.activity.SignUpActivity;

import java.util.UUID;

public class ConfirmFragment extends Fragment implements ConfirmContract.View {

    private TextView textPhoneEmail, textContent;
    private EditText editConfirmCode;
    private Button buttonConfirm, buttonResentCode;
    private ConfirmContract.Presenter presenter;
    private String firstName, lastName, phone, mail, password, confirmCode;
    private long dateOfBirth;
    private ProgressDialog dialog;

    public static ConfirmFragment newInstance(@Nullable String firstName,
                                              @Nullable String lastName,
                                              @Nullable long dateOfBirth,
                                              @Nullable String phone,
                                              @Nullable String email,
                                              @Nullable String password) {
        Bundle arguments = new Bundle();
        arguments.putString("firstName", firstName);
        arguments.putString("lastName", lastName);
        arguments.putLong("dateOfBirth", dateOfBirth);
        arguments.putString("phone", phone);
        arguments.putString("email", email);
        arguments.putString("password", password);
        ConfirmFragment fragment = new ConfirmFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up_confirm, container, false);
        textPhoneEmail = root.findViewById(R.id.text_sign_up_phone_email);
        textContent = root.findViewById(R.id.text_sign_up_content);
        editConfirmCode = root.findViewById(R.id.edit_confirm_code);
        buttonConfirm = root.findViewById(R.id.button_sign_up_confirm);
        buttonResentCode = root.findViewById(R.id.button_resent_code);

        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Đợi chút");

        ActionBar actionBar = ((SignUpActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(R.string.actionBar_sign_up_confirm);
        actionBar.setDisplayHomeAsUpEnabled(true);

        initPresenter();
        receiveData();

        presenter.handleCreateUI(phone, mail);
        return root;
    }

    private void receiveData() {
        firstName = getArguments().getString("firstName");
        lastName = getArguments().getString("lastName");
        dateOfBirth = getArguments().getLong("dateOfBirth");
        phone = getArguments().getString("phone");
        mail = getArguments().getString("email");
        password = getArguments().getString("password");
    }

    private void initPresenter() {
        ConfirmRepository repository = new ConfirmRepositoryImpl();
        presenter = new ConfirmPresenter(this, repository);
    }

    @Override
    public void onResume() {
        super.onResume();
        editConfirmCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                confirmCode = s.toString();
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                String uuid = UUID.randomUUID().toString().replace("-", "");
                Log.v("UUID", uuid);
                presenter.handleSignUp(confirmCode, phone, mail, password, uuid);
            }
        });
    }

    @Override
    public void showError(int msgResId) {
        dialog.dismiss();
        Toast.makeText(getContext(), getString(msgResId), Toast.LENGTH_LONG).show();
    }

    @SuppressLint("ShowToast")
    @Override
    public void nextLogin() {
        dialog.dismiss();
        Toast.makeText(getContext(), getString(R.string.notify_create_account_successful), Toast.LENGTH_LONG);
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateCreateUI(Boolean isPhone) {
        if (isPhone) {
            textPhoneEmail.setText(phone);
            textContent.setText("Nhập mã gồm 5 chữ số từ SMS của bạn");
        } else {
            textPhoneEmail.setText(mail);
            textContent.setText("Nhập mã gồm 5 chữ số từ Email của bạn");
        }
    }
}
