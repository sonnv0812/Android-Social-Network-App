package com.example.facebookapp.ui.home.friend;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.facebookapp.R;
import com.example.facebookapp.listener.FriendRequestClickListener;
import com.example.facebookapp.data.model.friend.Friend;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

public class FriendRequestViewHolder extends RecyclerView.ViewHolder {

    private Friend item;

    private TextView textFriendName, textTimeAgo;
    private ImageView imageFriendAvatar;
    private Button buttonAccept, buttonDelete;
    private WeakReference<FriendRequestClickListener> listenerRef;

    public FriendRequestViewHolder(@NonNull View itemView, FriendRequestClickListener listener) {
        super(itemView);

        listenerRef = new WeakReference<>(listener);
        textFriendName = itemView.findViewById(R.id.text_friend_name);
        textTimeAgo = itemView.findViewById(R.id.text_time_request_ago);
        imageFriendAvatar = itemView.findViewById(R.id.image_friend_avatar);
        buttonAccept = itemView.findViewById(R.id.button_accept_request);
        buttonDelete = itemView.findViewById(R.id.button_delete_request);

        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRef.get().onAcceptClicked(getAdapterPosition());
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRef.get().onDeleteClicked(getAdapterPosition());
            }
        });

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
        Glide.with(itemView.getContext()).load(friend.getAvatar()).into(imageFriendAvatar);
    }

}
