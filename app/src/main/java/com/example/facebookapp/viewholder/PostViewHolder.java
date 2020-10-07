package com.example.facebookapp.viewholder;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapp.R;
import com.example.facebookapp.model.PostModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.util.Calendar;

public class PostViewHolder extends RecyclerView.ViewHolder {
    private PostModel itemData;

    private ImageView imageAvatar;
    private TextView textName;
    private TextView textTime;
    private TextView textPost;
    private TextView textNumberLike;
    private Button buttonLike;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        imageAvatar = itemView.findViewById(R.id.image_avatar);
        textName = itemView.findViewById(R.id.text_name);
        textTime = itemView.findViewById(R.id.text_timePost);
        textPost = itemView.findViewById(R.id.text_post);
        textNumberLike = itemView.findViewById(R.id.text_numberLike);
        buttonLike = itemView.findViewById(R.id.button_like);
    }


    public void bindData(final PostModel postModel) {
        Picasso.get().load(Uri.parse(postModel.getAvatar())).into(imageAvatar);

        textName.setText(postModel.getNameAccount());
//        long timeAgo = Calendar.getInstance().getTimeInMillis() - postModel.getTimePost();
        textTime.setText(String.format("%sh ago", String.valueOf(postModel.getTimePost())));
        textPost.setText(postModel.getTextPost());
        textNumberLike.setText(String.valueOf(postModel.getNumberLike()));
        if (postModel.isLike())
            buttonLike.setTextColor(Color.BLUE);
        else
            buttonLike.setTextColor(Color.BLACK);
        buttonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLike = postModel.isLike();
                postModel.setLike(!isLike);

            }
        });
    }
}
