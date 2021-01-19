package com.example.facebookapp.ui.friend.all;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.facebookapp.R;
import com.example.facebookapp.listener.FriendUserClickListener;
import com.example.facebookapp.data.model.friend.Friend;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

public class AllFriendViewHolder extends RecyclerView.ViewHolder {

    protected TextView textFriendName, textSameFriend;
    protected ImageView imageFriendAvatar;
    protected ImageButton imgButtonAdvanced;
    protected WeakReference<FriendUserClickListener> listenerRef;

    public AllFriendViewHolder(@NonNull View itemView, FriendUserClickListener listener) {
        super(itemView);

        listenerRef = new WeakReference<FriendUserClickListener>(listener);
        textFriendName = itemView.findViewById(R.id.text_friend_name);
        imageFriendAvatar = itemView.findViewById(R.id.image_friend_avatar);
        textSameFriend = itemView.findViewById(R.id.text_same_friend);
        imgButtonAdvanced = itemView.findViewById(R.id.image_setup_friend_advanced);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRef.get().onClick(getAdapterPosition());
            }
        });

        imgButtonAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRef.get().onSetupClick(getAdapterPosition());
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void bindData(Friend friend) {
        if (friend.getSameFriend() == 0)
            textSameFriend.setText("");
        else
            textSameFriend.setText(friend.getSameFriend() + " bạn chung");

        textFriendName.setText(friend.getUsername());
        Picasso.get().load(friend.getAvatar()).into(imageFriendAvatar);
    }
}
