package com.example.facebookapp.ui.dialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.facebookapp.R;
import com.example.facebookapp.ui.post.edit.EditPostActivity;

public class DeletePostDialog extends DialogFragment implements View.OnClickListener {

    private Button buttonEditPost, buttonCancel, buttonDeletePost;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_delete_post, container, false);
        buttonCancel = view.findViewById(R.id.button_cancel);
        buttonDeletePost = view.findViewById(R.id.button_delete_post);
        buttonEditPost = view.findViewById(R.id.button_continue_edit);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        buttonCancel.setOnClickListener(this);
        buttonEditPost.setOnClickListener(this);
        buttonDeletePost.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_cancel:
                dismiss();
                break;
            case R.id.button_delete_post:
                break;
            case R.id.button_continue_edit:
                Intent intent = new Intent(getContext(), EditPostActivity.class);
                startActivity(intent);
                break;
        }
    }
}
