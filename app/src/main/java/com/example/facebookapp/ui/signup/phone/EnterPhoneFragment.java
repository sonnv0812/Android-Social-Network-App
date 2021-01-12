package com.example.facebookapp.ui.signup.phone;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.facebookapp.R;
import com.example.facebookapp.ui.signup.activity.SignUpActivity;
import com.example.facebookapp.ui.signup.mail.EnterMailFragment;
import com.example.facebookapp.ui.signup.password.EnterPasswordFragment;
import com.google.android.material.textfield.TextInputLayout;

public class EnterPhoneFragment extends Fragment implements EnterPhoneContract.View {

    private Button buttonNextConfirm;
    private TextView textRegisterEmail;
    private TextInputLayout layoutPhone;
    private EditText editPhone;
    private String phone;
    private EnterPhoneContract.Presenter presenter;
    private String firstName, lastName;
    private long dateOfBirth;

    public static EnterPhoneFragment newInstance(@Nullable String firstName,
                                                 @Nullable String lastName,
                                                 @Nullable long dateOfBirth) {
        Bundle arguments = new Bundle();
        arguments.putString("firstName", firstName);
        arguments.putString("lastName", lastName);
        arguments.putLong("dateOfBirth", dateOfBirth);
        EnterPhoneFragment fragment = new EnterPhoneFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up_phone, container, false);
        ActionBar actionBar = ((SignUpActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(R.string.actionbar_sign_up_phone);
        actionBar.setDisplayHomeAsUpEnabled(true);
        buttonNextConfirm = root.findViewById(R.id.button_next_password);
        textRegisterEmail = root.findViewById(R.id.text_register_email);
        editPhone = root.findViewById(R.id.edit_phone_signUp);
        layoutPhone = root.findViewById(R.id.layout_phone_signUp);
        initPresenter();
        receiveData();
        return root;
    }

    private void receiveData() {
        firstName = getArguments().getString("firstName");
        lastName = getArguments().getString("lastName");
        dateOfBirth = getArguments().getLong("dateOfBirth");
    }

    private void initPresenter() {
        presenter = new EnterPhonePresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        textRegisterEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_sign_up,
                                EnterMailFragment.newInstance(firstName, lastName, dateOfBirth))
                        .commit();
            }
        });

        buttonNextConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_sign_up,
                                EnterPasswordFragment.newInstance(firstName, lastName, dateOfBirth, phone, null))
                        .addToBackStack(null)
                        .commit();
            }
        });

        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone = s.toString();
                presenter.checkValidatePhone(phone);
            }

            @Override
            public void afterTextChanged(Editable s) {
                phone = s.toString();
                presenter.checkValidatePhone(phone);
            }
        });
    }

    @Override
    public void showError(int msgResId) {
        layoutPhone.setError(getString(msgResId));
    }
}
