package com.example.facebookapp.ui.home.friend;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapp.R;
import com.example.facebookapp.config.FriendOnClickListener;
import com.example.facebookapp.data.model.friend.Friend;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

public class FriendRequestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Friend item;

    private TextView textFriendName, textTimeAgo;
    private ImageView imageFriendAvatar;
    private Button buttonAccept, buttonDelete;
    private WeakReference<FriendOnClickListener> listenerRef;

    public FriendRequestViewHolder(@NonNull View itemView, FriendOnClickListener listener) {
        super(itemView);

        listenerRef = new WeakReference<>(listener);
        textFriendName = itemView.findViewById(R.id.text_friend_name);
        textTimeAgo = itemView.findViewById(R.id.text_time_request_ago);
        imageFriendAvatar = itemView.findViewById(R.id.image_friend_avatar);
        buttonAccept = itemView.findViewById(R.id.button_accept_request);
        buttonDelete = itemView.findViewById(R.id.button_delete_request);

        buttonAccept.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRef.get().onClicked(getAdapterPosition());
            }
        });

    }

    public void bindData(Friend friend) {
        item = friend;

        textTimeAgo.setText(friend.getCreated());
        textFriendName.setText(friend.getUsername());
        Picasso.get().load(friend.getAvatar()).into(imageFriendAvatar);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_accept_request:
                listenerRef.get().onAcceptClicked(getAdapterPosition());
                break;
            case R.id.button_delete_request:
                listenerRef.get().onDeleteClicked(getAdapterPosition());
                break;
        }
    }
}
