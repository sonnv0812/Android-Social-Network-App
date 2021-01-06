package com.example.facebookapp.ui.home.notification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapp.R;
import com.example.facebookapp.data.model.NotificationModel;
import com.example.facebookapp.ui.home.notification.NotificationViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder> {

    private List<NotificationModel> notifications = new ArrayList<>();

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.bindData(notifications.get(position));
    }

    @Override
    public int getItemCount() {
        return notifications != null ? notifications.size() : 0;
    }

    public void updateData(List<NotificationModel> newNotifications) {
        notifications.clear();
        newNotifications.addAll(newNotifications);
        notifyDataSetChanged();
    }

    public void addData(List<NotificationModel> moreNotifications) {
        int oldSize = getItemCount();
        notifications.addAll(moreNotifications);
        int newSize = getItemCount();
        notifyItemRangeChanged(oldSize, newSize);
    }
}
