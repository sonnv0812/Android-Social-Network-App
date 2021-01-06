package com.example.facebookapp.ui.signup.password;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.facebookapp.R;
import com.example.facebookapp.ui.signup.confirm.ConfirmFragment;
import com.example.facebookapp.ui.signup.mail.EnterMailFragment;
import com.google.android.material.textfield.TextInputLayout;

public class EnterPasswordFragment extends Fragment implements EnterPasswordContract.View {

    private EditText editPassword, editConfirm;
    private TextInputLayout layoutPassword, layoutConfirm;
    private EnterPasswordContract.Presenter presenter;
    private String password, confirmPassword;
    private String firstName, lastName, phone, mail;
    private long dateOfBirth;
    private Button buttonNextConfirm;

    public static EnterPasswordFragment newInstance(@Nullable String firstName,
                                                    @Nullable String lastName,
                                                    @Nullable long dateOfBirth,
                                                    @Nullable String phone,
                                                    @Nullable String email) {
        Bundle arguments = new Bundle();
        arguments.putString("firstName", firstName);
        arguments.putString("lastName", lastName);
        arguments.putLong("dateOfBirth", dateOfBirth);
        arguments.putString("phone", phone);
        arguments.putString("email", email);
        EnterPasswordFragment fragment = new EnterPasswordFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up_password, container, false);
        editPassword = root.findViewById(R.id.edit_password_signUp);
        editConfirm = root.findViewById(R.id.edit_confirm_signUp);
        layoutPassword = root.findViewById(R.id.layout_password_signUp);
        layoutConfirm = root.findViewById(R.id.layout_confirm_signUp);
        buttonNextConfirm = root.findViewById(R.id.button_next_confirm_signUp);
        initPresenter();
        receiveData();
        return root;
    }

    private void receiveData() {
        firstName = getArguments().getString("firstName");
        lastName = getArguments().getString("lastName");
        dateOfBirth = getArguments().getLong("dateOfBirth");
        phone = getArguments().getString("phone");
        mail = getArguments().getString("email");
    }

    private void initPresenter() {
        presenter = new EnterPasswordPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = s.toString();
                presenter.checkValidatePassword(password);
            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
                presenter.checkValidatePassword(password);
            }
        });

        editConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                confirmPassword = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                confirmPassword = s.toString();
            }
        });

        buttonNextConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checkConfirmPassword(password, confirmPassword);
            }
        });
    }

    @Override
    public void showPasswordError(int msgResId) {
        layoutPassword.setError(getString(msgResId));
    }

    @Override
    public void showConfirmPassError(int msgResId) {
        layoutConfirm.setError(getString(msgResId));
    }

    @Override
    public void nextConfirm() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_sign_up,
                        ConfirmFragment.newInstance(firstName, lastName, dateOfBirth, phone, mail, password))
                .addToBackStack(null)
                .commit();
    }
}
