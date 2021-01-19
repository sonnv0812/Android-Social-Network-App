package com.example.facebookapp.ui.bottomsheet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.facebookapp.R;
import com.example.facebookapp.ui.home.activity.HomeActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PopupBackCreateStatus extends BottomSheetDialogFragment implements View.OnClickListener {

    private ConstraintLayout constraintSave, constraintQuit, constraintContinue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_fragment_action, container, false);
        constraintSave = view.findViewById(R.id.constraint_save_draft);
        constraintQuit = view.findViewById(R.id.constraint_quit_post);
        constraintContinue = view.findViewById(R.id.constraint_continue_edit);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        constraintSave.setOnClickListener(this);
        constraintQuit.setOnClickListener(this);
        constraintContinue.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.constraint_save_draft:
                break;
            case R.id.constraint_quit_post:
                getActivity().onBackPressed();
                break;
            case R.id.constraint_continue_edit:
                dismiss();
                break;
        }
    }
}
