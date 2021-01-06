package com.example.facebookapp.ui.signup.name;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.facebookapp.R;
import com.example.facebookapp.ui.signup.activity.SignUpActivity;
import com.example.facebookapp.ui.signup.birthday.EnterBirthdayFragment;
import com.google.android.material.textfield.TextInputLayout;

public class EnterNameFragment extends Fragment implements EnterNameContract.View {

    private Button buttonNextBirthDay;
    private TextInputLayout layoutFirstName, layoutLastName;
    private EditText editFirstName, editLastName;
    private String firstName, lastName;
    private EnterNamePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up_name, container, false);

        ActionBar actionBar = ((SignUpActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(R.string.actionBar_sign_up_name);
        actionBar.setDisplayHomeAsUpEnabled(true);

        layoutFirstName = root.findViewById(R.id.editLayout_signUp_firstName);
        layoutLastName = root.findViewById(R.id.editLayout_signUp_lastName);
        editFirstName = root.findViewById(R.id.edit_signUp_firstName);
        editLastName = root.findViewById(R.id.edit_signUp_lastName);
        buttonNextBirthDay = root.findViewById(R.id.button_next_birthday);
        presenter = new EnterNamePresenter(this);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        buttonNextBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checkInput(firstName, lastName);
            }
        });

        editFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                firstName = s.toString();
                presenter.checkFirstName(firstName);
            }

            @Override
            public void afterTextChanged(Editable s) {
                firstName = s.toString();
                presenter.checkFirstName(firstName);
            }
        });

        editLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lastName = s.toString();
                presenter.checkLastName(lastName);
            }

            @Override
            public void afterTextChanged(Editable s) {
                lastName = s.toString();
                presenter.checkLastName(lastName);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showErrorFirstName(int msgResId) {
        layoutFirstName.setError(getString(msgResId));
    }

    @Override
    public void showErrorLastName(int msgResId) {
        layoutLastName.setError(getString(msgResId));
    }

    @Override
    public void nextEnterBirthday() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_sign_up, EnterBirthdayFragment.newInstance(firstName, lastName))
                .addToBackStack(null)
                .commit();
    }
}
