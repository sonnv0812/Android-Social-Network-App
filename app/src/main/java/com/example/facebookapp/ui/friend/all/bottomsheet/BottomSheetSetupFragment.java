package com.example.facebookapp.ui.friend.all.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.facebookapp.R;
import com.example.facebookapp.ui.status.create.bottomsheet.BottomSheetFragment;

public class BottomSheetSetupFragment extends BottomSheetFragment {

    private ConstraintLayout constraintMessage, constraintBlock, constraintUnFriend;
    private TextView textLastName, textFriendName;
    private ImageView imageAvatar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_friend_advance_setup, container, false);

        constraintMessage = view.findViewById(R.id.constraint_message);
        constraintBlock = view.findViewById(R.id.constraint_block);
        constraintUnFriend = view.findViewById(R.id.constraint_un_friend);
        textFriendName = view.findViewById(R.id.text_friend_name);
        textLastName = view.findViewById(R.id.text_first_name);
        imageAvatar = view.findViewById(R.id.image_friend_avatar);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
