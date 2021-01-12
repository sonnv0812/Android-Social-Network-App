package com.example.facebookapp.ui.signup.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.facebookapp.R;
import com.example.facebookapp.ui.login.LoginActivity;
import com.example.facebookapp.ui.signup.activity.SignUpActivity;
import com.example.facebookapp.ui.signup.name.EnterNameFragment;

public class WelcomeFragment extends Fragment {

    private Button buttonNextEnterName;
    private TextView textLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ActionBar actionBar = ((SignUpActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(R.string.actionbar_sign_up_welcome);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        View root = inflater.inflate(R.layout.fragment_sign_up_welcome, container, false);
        buttonNextEnterName = root.findViewById(R.id.button_next_enter_name);
        textLogin = root.findViewById(R.id.text_button_login);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        buttonNextEnterName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_sign_up, new EnterNameFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
