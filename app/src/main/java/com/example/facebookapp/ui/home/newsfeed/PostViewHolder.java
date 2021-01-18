package com.example.facebookapp.ui.home.newsfeed;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.facebookapp.R;
import com.example.facebookapp.data.model.post.Post;
import com.example.facebookapp.listener.PostClickListener;

import java.lang.ref.WeakReference;

public class PostViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageAvatar;
    private TextView textName;
    private TextView textTime;
    private TextView textDescribed;
    private TextView textNumberLike;
    private Button buttonLike;
    private WeakReference<PostClickListener> listenerRef;

    public PostViewHolder(@NonNull final View itemView, PostClickListener listener) {
        super(itemView);

        listenerRef = new WeakReference<PostClickListener>(listener);
        imageAvatar = itemView.findViewById(R.id.image_avatar);
        textName = itemView.findViewById(R.id.text_name);
        textTime = itemView.findViewById(R.id.text_timePost);
        textDescribed = itemView.findViewById(R.id.text_post);
        textNumberLike = itemView.findViewById(R.id.text_numberLike);
        buttonLike = itemView.findViewById(R.id.button_like);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRef.get().onClick(getAdapterPosition());
            }
        });

        buttonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRef.get().onLikeClick(getAdapterPosition());
            }
        });
    }

    public void bindData(Post post) {
        Glide.with(itemView).load(post.getAuthor().getAvatar()).into(imageAvatar);
        textName.setText(post.getAuthor().getName());
        textDescribed.setText(post.getDescribed());
        textNumberLike.setText(post.getLike());
    }

}
