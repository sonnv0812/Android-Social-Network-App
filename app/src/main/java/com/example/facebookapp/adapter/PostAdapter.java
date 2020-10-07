package com.example.facebookapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapp.R;
import com.example.facebookapp.model.PostModel;
import com.example.facebookapp.viewholder.OnItemClickListener;
import com.example.facebookapp.viewholder.PostViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {
    private List<PostModel> posts = new ArrayList<>();
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {
        holder.bindData(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts != null ? posts.size() : 0;
    }

    public void updateData(List<PostModel> newPosts) {
        posts.clear();
        posts.addAll(newPosts);
        notifyDataSetChanged();
    }

    public void addData(List<PostModel> morePosts) {
        int oldSize = getItemCount();
        posts.addAll(morePosts);
        int newSize = getItemCount();
        notifyItemRangeChanged(oldSize, newSize);
    }
}
