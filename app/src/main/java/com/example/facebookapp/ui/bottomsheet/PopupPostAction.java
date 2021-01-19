package com.example.facebookapp.ui.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.facebookapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PopupPostAction extends BottomSheetDialogFragment {

    private ConstraintLayout constraintDeletePost, constraintNotify, constraintEdit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_post_action, container, false);

        constraintDeletePost = view.findViewById(R.id.constraint_delete_post);
        constraintEdit = view.findViewById(R.id.constraint_edit_post);
        constraintNotify = view.findViewById(R.id.constraint_notify_post);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        constraintDeletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        constraintNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        constraintEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
