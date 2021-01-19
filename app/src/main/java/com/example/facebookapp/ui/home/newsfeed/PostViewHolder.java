package com.example.facebookapp.ui.home.newsfeed;

import android.graphics.Color;
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

    private ImageView imageAvatar, imageIconLike, imageButtonAction;
    private TextView textName;
    private TextView textTime;
    private TextView textDescribed;
    private TextView textNumberLike, textNumberComment;
    private Button buttonLike;
    private WeakReference<PostClickListener> listenerRef;

    public PostViewHolder(@NonNull final View itemView, PostClickListener listener) {
        super(itemView);

        listenerRef = new WeakReference<PostClickListener>(listener);
        imageAvatar = itemView.findViewById(R.id.image_avatar);
        imageIconLike = itemView.findViewById(R.id.image_ic_like);
        imageButtonAction = itemView.findViewById(R.id.image_ic_other);
        textName = itemView.findViewById(R.id.text_name);
        textTime = itemView.findViewById(R.id.text_timePost);
        textDescribed = itemView.findViewById(R.id.text_post);
        textNumberLike = itemView.findViewById(R.id.text_numberLike);
        textNumberComment = itemView.findViewById(R.id.text_number_comment);
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

        imageButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRef.get().onActionClick(getAdapterPosition());
            }
        });
    }

    public void bindData(Post post) {
        Glide.with(itemView).load(post.getAuthor().getAvatar()).into(imageAvatar);
        textName.setText(post.getAuthor().getName());
        textDescribed.setText(post.getDescribed());
        textNumberLike.setText(String.valueOf(post.getLike()));
        textNumberComment.setText(post.getComment() + " bình luận");

        if (post.getIsLiked() == 0) {
            buttonLike.setTextColor(Color.BLACK);
            textNumberLike.setTextColor(Color.BLACK);
            imageIconLike.setImageResource(R.drawable.ic_like_black);
        }
        else {
            buttonLike.setTextColor(Color.BLUE);
            textNumberLike.setTextColor(Color.BLUE);
            imageIconLike.setImageResource(R.drawable.ic_like_blue);
        }
    }
}
