package com.example.facebookapp.ui.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.example.facebookapp.R;
import com.example.facebookapp.ui.home.activity.HomeActivity;

public class BackEditPostDialog extends DialogFragment implements View.OnClickListener {

    private Button buttonContinueEdit, buttonRemove;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_back_edit_post, container, false);
        buttonContinueEdit = view.findViewById(R.id.button_continue_edit);
        buttonRemove = view.findViewById(R.id.button_remove);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        buttonRemove.setOnClickListener(this);
        buttonContinueEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_continue_edit:
                dismiss();
                break;
            case R.id.button_remove:
                Intent intent = new Intent(getContext(), HomeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
