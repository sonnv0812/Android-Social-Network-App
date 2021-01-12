package com.example.facebookapp.ui.friend.suggest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapp.R;
import com.example.facebookapp.listener.FriendSuggestClickListener;
import com.example.facebookapp.data.model.friend.Friend;

import java.util.List;

public class SuggestFriendAdapter extends RecyclerView.Adapter<SuggestFriendViewHolder> {

    private List<Friend> friendList;
    private FriendSuggestClickListener listener;

    public SuggestFriendAdapter(List<Friend> friendList, FriendSuggestClickListener listener) {
        this.friendList = friendList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SuggestFriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_suggest, parent, false);
        return new SuggestFriendViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestFriendViewHolder holder, int position) {
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
