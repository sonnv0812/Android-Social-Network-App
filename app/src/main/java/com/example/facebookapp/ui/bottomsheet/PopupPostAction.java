package com.example.facebookapp.ui.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.facebookapp.R;
import com.example.facebookapp.listener.PostActionListener;
import com.example.facebookapp.listener.PostClickListener;
import com.example.facebookapp.ui.dialog.DeletePostDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.lang.ref.WeakReference;

public class PopupPostAction extends BottomSheetDialogFragment {

    private ConstraintLayout constraintDeletePost, constraintNotify, constraintEdit;
    private PostActionListener listener;
    private WeakReference<PostActionListener> listenerRef;

    public PopupPostAction(PostActionListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_post_action, container, false);

        listenerRef = new WeakReference<PostActionListener>(listener);
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
                DeletePostDialog deletePostDialog = new DeletePostDialog();
                deletePostDialog.show(getFragmentManager(), deletePostDialog.getTag());
            }
        });

        constraintNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRef.get().onSetupNotify();
            }
        });

        constraintEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRef.get().onEditPost();
            }
        });
    }
}
