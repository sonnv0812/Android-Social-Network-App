package com.example.facebookapp.ui.friend.all;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapp.R;
import com.example.facebookapp.listener.FriendUserClickListener;
import com.example.facebookapp.data.model.friend.Friend;

import java.util.ArrayList;
import java.util.List;

public class AllFriendAdapter extends RecyclerView.Adapter<AllFriendViewHolder> {

    private List<Friend> friendList = new ArrayList<>();
    private FriendUserClickListener listener;

    public AllFriendAdapter(List<Friend> friendList, FriendUserClickListener listener) {
        this.friendList = friendList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AllFriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        return new AllFriendViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AllFriendViewHolder holder, int position) {
        holder.bindData(friendList.get(position));
    }

    @Override
    public int getItemCount() {
        return friendList == null ? 0 : friendList.size();
    }

    public void updateData(List<Friend> newFriend) {
        friendList.clear();
        friendList.addAll(newFriend);
        notifyDataSetChanged();
    }

    public void addData(List<Friend> moreFriend) {
        int oldSize = friendList.size();
        friendList.addAll(moreFriend);
        int newSize = friendList.size();
        notifyItemRangeChanged(oldSize, newSize);
    }
}
