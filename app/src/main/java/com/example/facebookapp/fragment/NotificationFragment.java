package com.example.facebookapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapp.R;
import com.example.facebookapp.adapter.NotificationAdapter;
import com.example.facebookapp.model.NotificationModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {

    private RecyclerView recyclerView;
    private NotificationAdapter notificationAdapter = new NotificationAdapter();

    public NotificationFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_notification);
        recyclerView.setAdapter(notificationAdapter);
        initData();
        return view;
    }

    public void initData() {
        List<NotificationModel> notifications = new ArrayList<>();

        notifications.add(new NotificationModel (
                "https://wallpapercave.com/wp/k4cxWQK.jpg",
                "Nghia Dinh mentioned you in a comment"
        ));
        notifications.add(new NotificationModel (
                "https://wallpapercave.com/wp/k4cxWQK.jpg",
                "Nghia Dinh mentioned you in a comment"
        ));
        notifications.add(new NotificationModel (
                "https://wallpapercave.com/wp/k4cxWQK.jpg",
                "Nghia Dinh mentioned you in a comment"
        ));
        notifications.add(new NotificationModel (
                "https://wallpapercave.com/wp/k4cxWQK.jpg",
                "Nghia Dinh mentioned you in a comment"
        ));

        notificationAdapter.addData(notifications);
    }
}
