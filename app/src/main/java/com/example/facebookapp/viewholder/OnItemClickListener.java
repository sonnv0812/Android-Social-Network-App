package com.example.facebookapp.viewholder;

import android.view.View;

import com.example.facebookapp.model.PostModel;

public interface OnItemClickListener {
    void onClick(PostModel view, int position);
}
