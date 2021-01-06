package com.example.facebookapp.ui.signup.birthday;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.facebookapp.R;
import com.example.facebookapp.ui.signup.activity.SignUpActivity;
import com.example.facebookapp.ui.signup.rules.RulesFragment;

import java.sql.Timestamp;
import java.util.Date;

public class EnterBirthdayFragment extends Fragment implements EnterBirthdayContract.View {

    private Button buttonNextRules;
    private EnterBirthdayContract.Presenter presenter;
    private DatePicker datePickerBirthday;
    private Timestamp dateOfBirth;

    public static EnterBirthdayFragment newInstance(@Nullable String firstName,
                                                    @Nullable String lastName) {
        Bundle arguments = new Bundle();
        arguments.putString("firstName", firstName);
        arguments.putString("lastName", lastName);
        EnterBirthdayFragment fragment = new EnterBirthdayFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ActionBar actionBar = ((SignUpActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(R.string.actionBar_sign_up_birthday);
        actionBar.setDisplayHomeAsUpEnabled(true);
        View root = inflater.inflate(R.layout.fragment_sign_up_birthday, container, false);
        buttonNextRules = root.findViewById(R.id.button_next_rules);
        datePickerBirthday = root.findViewById(R.id.picker_birthday);
        initPresenter();
        initUI();
        return root;
    }

    private void initPresenter() {
        presenter = new EnterBirthdayPresenter(this);
    }

    private void initUI() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        super.onResume();
        buttonNextRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = getArguments().getString("firstName");
                String lastName = getArguments().getString("lastName");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_sign_up, RulesFragment.newInstance(firstName, lastName, dateOfBirth.getTime()))
                        .addToBackStack(null)
                        .commit();
            }
        });
        datePickerBirthday.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                presenter.handleConvertDate(dayOfMonth, monthOfYear, year);
            }
        });
    }

    @Override
    public void showMsg(int msgResId) {

    }

    @Override
    public void confirmDate(Date date) {
        dateOfBirth = new Timestamp(date.getTime());
    }
}
