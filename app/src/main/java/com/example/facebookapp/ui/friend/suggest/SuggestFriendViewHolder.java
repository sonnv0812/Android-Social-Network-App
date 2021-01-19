package com.example.facebookapp.ui.friend.suggest;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.facebookapp.R;
import com.example.facebookapp.listener.FriendSuggestClickListener;
import com.example.facebookapp.data.model.friend.Friend;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

public class SuggestFriendViewHolder extends RecyclerView.ViewHolder {

    protected TextView textFriendName;
    protected ImageView imageFriendAvatar;
    private Button buttonAdd, buttonRemove;
    protected WeakReference<FriendSuggestClickListener> listenerRef;

    public SuggestFriendViewHolder(@NonNull View itemView, FriendSuggestClickListener listener) {
        super(itemView);
        listenerRef = new WeakReference<FriendSuggestClickListener>(listener);
        textFriendName = itemView.findViewById(R.id.text_friend_name);
        imageFriendAvatar = itemView.findViewById(R.id.image_friend_avatar);
        buttonAdd = itemView.findViewById(R.id.button_add_friend);
        buttonRemove = itemView.findViewById(R.id.button_remove_suggest);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRef.get().onClick(getAdapterPosition());
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRef.get().onAddClick(getAdapterPosition());
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRef.get().onRemoveClick(getAdapterPosition());
            }
        });
    }

    public void bindData(Friend friend) {
        textFriendName.setText(friend.getUsername());
        Picasso.get().load(friend.getAvatar()).into(imageFriendAvatar);
    }
}
