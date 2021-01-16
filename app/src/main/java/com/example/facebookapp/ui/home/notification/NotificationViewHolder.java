package com.example.facebookapp.ui.home.notification;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapp.R;
import com.example.facebookapp.data.model.notification.NotificationModel;
import com.squareup.picasso.Picasso;

public class NotificationViewHolder extends RecyclerView.ViewHolder {


    private ImageView imageAva;
    private TextView textNotification;

    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);

        imageAva = itemView.findViewById(R.id.image_ava_notification);
        textNotification = itemView.findViewById(R.id.text_notification);
    }

    public void bindData(final NotificationModel notificationModel) {
        Picasso.get().load(Uri.parse(notificationModel.getAvaNotification())).into(imageAva);
        textNotification.setText(notificationModel.getTextNotification());
    }
}
