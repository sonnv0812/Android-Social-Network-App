package com.example.facebookapp.ui.signup.rules;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.facebookapp.R;
import com.example.facebookapp.ui.signup.activity.SignUpActivity;
import com.example.facebookapp.ui.signup.mail.EnterMailFragment;

import java.util.Date;

public class RulesFragment extends Fragment {

    private WebView webRules, webContent;
    private Button buttonNextSignUp;

    public static RulesFragment newInstance(@Nullable String firstName,
                                            @Nullable String lastName,
                                            @Nullable long dateOfBirth) {
        Bundle arguments = new Bundle();
        arguments.putString("firstName", firstName);
        arguments.putString("lastName", lastName);
        arguments.putLong("dateOfBirth", dateOfBirth);
        RulesFragment fragment = new RulesFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up_rules_view, container, false);
        ActionBar actionBar = ((SignUpActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(R.string.actionBar_sign_up_rules);
        actionBar.setDisplayHomeAsUpEnabled(true);
        webRules = root.findViewById(R.id.webView_rules);
        webContent = root.findViewById(R.id.webView_content);
        buttonNextSignUp = root.findViewById(R.id.button_next_signUp);
        initUI();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        buttonNextSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = getArguments().getString("firstName");
                String lastName = getArguments().getString("lastName");
                long dateOfBirth = getArguments().getLong("dateOfBirth");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_sign_up, EnterMailFragment.newInstance(firstName, lastName, dateOfBirth))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void initUI() {
        webRules.setBackgroundColor(0xd4d4d4);
        webContent.setBackgroundColor(0xd4d4d4);
        showStaticContent();
    }

    private void showStaticContent() {
        webRules.loadData(Rules.RULES, "text/html", "UTF-8");
        webContent.loadData(Rules.CONTENT, "text/html", "UTF-8");
    }
}
