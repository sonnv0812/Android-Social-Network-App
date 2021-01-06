package com.example.facebookapp.ui.signup.mail;

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
import com.example.facebookapp.ui.signup.confirm.ConfirmFragment;
import com.example.facebookapp.ui.signup.password.EnterPasswordFragment;
import com.example.facebookapp.ui.signup.phone.EnterPhoneFragment;
import com.example.facebookapp.ui.signup.rules.RulesFragment;
import com.google.android.material.textfield.TextInputLayout;

public class EnterMailFragment extends Fragment implements EnterMailContract.View {

    private Button buttonNextConfirm;
    private TextView textRegisterPhone;
    private EnterMailContract.Presenter presenter;
    private TextInputLayout layoutMail;
    private EditText editMail;
    private String mail;
    private String firstName, lastName;
    private long dateOfBirth;

    public static EnterMailFragment newInstance(@Nullable String firstName,
                                                @Nullable String lastName,
                                                @Nullable long dateOfBirth) {
        Bundle arguments = new Bundle();
        arguments.putString("firstName", firstName);
        arguments.putString("lastName", lastName);
        arguments.putLong("dateOfBirth", dateOfBirth);
        EnterMailFragment fragment = new EnterMailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up_email, container, false);
        ActionBar actionBar = ((SignUpActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(R.string.actionBar_sign_up_email);
        actionBar.setDisplayHomeAsUpEnabled(true);
        textRegisterPhone = root.findViewById(R.id.text_register_phone);
        buttonNextConfirm = root.findViewById(R.id.button_next_password);
        layoutMail = root.findViewById(R.id.layout_mail_signUp);
        editMail = root.findViewById(R.id.edit_mail_signUp);
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
        presenter = new EnterMailPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        textRegisterPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_sign_up, EnterPhoneFragment.newInstance(firstName, lastName, dateOfBirth))
                        .commit();
            }
        });

        buttonNextConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_sign_up,
                                EnterPasswordFragment.newInstance(firstName, lastName, dateOfBirth, null, mail))
                        .addToBackStack(null)
                        .commit();
            }
        });
        editMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mail = s.toString();
                presenter.checkValidateMail(mail);
            }

            @Override
            public void afterTextChanged(Editable s) {
                mail = s.toString();
                presenter.checkValidateMail(mail);
            }
        });
    }

    @Override
    public void showError(int msgResId) {
        layoutMail.setError(getString(msgResId));
    }
}
