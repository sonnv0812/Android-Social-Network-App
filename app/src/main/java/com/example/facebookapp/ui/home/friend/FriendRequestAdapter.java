package com.example.facebookapp.ui.home.friend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapp.R;
import com.example.facebookapp.config.FriendOnClickListener;
import com.example.facebookapp.data.model.friend.Friend;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestViewHolder> {

    private List<Friend> friends = new ArrayList<>();
    private FriendOnClickListener listener;

    public FriendRequestAdapter(List<Friend> friends, FriendOnClickListener listener) {
        this.friends = friends;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FriendRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_request, parent, false);
        return new FriendRequestViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendRequestViewHolder holder, int position) {
        holder.bindData(friends.get(position));
    }

    @Override
    public int getItemCount() {
        return friends == null ? 0 : friends.size();
    }

    public void updateData(List<Friend> newFriend) {
        friends.clear();
        friends.addAll(newFriend);
        notifyDataSetChanged();
    }

    public void addData(List<Friend> moreFriend) {
        int oldSize = friends.size();
        friends.addAll(moreFriend);
        int newSize = friends.size();
        notifyItemRangeChanged(oldSize, newSize);
    }
}
