package com.example.facebookapp.ui.home.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.facebookapp.R;
import com.example.facebookapp.ui.home.activity.HomeActivity;

public class VideoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_video, container, false);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = ((HomeActivity) getActivity()).getSupportActionBar();
        actionBar.hide();
    }
}
