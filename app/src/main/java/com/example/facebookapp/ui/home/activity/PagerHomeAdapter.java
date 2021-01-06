package com.example.facebookapp.ui.home.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.facebookapp.config.FragmentsHome;
import com.example.facebookapp.ui.home.group.GroupFragment;
import com.example.facebookapp.ui.home.newfeed.NewFeedFragment;
import com.example.facebookapp.ui.home.menu.MenuFragment;
import com.example.facebookapp.ui.home.notification.NotificationFragment;
import com.example.facebookapp.ui.home.video.VideoFragment;

public class PagerHomeAdapter extends FragmentPagerAdapter {

    public PagerHomeAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case FragmentsHome.HOME:
                fragment = new NewFeedFragment();
                break;
            case FragmentsHome.VIDEO:
                fragment = new VideoFragment();
                break;
            case FragmentsHome.NOTIFICATION:
                fragment = new NotificationFragment();
                break;
            case FragmentsHome.GROUP:
                fragment = new GroupFragment();
                break;
            case FragmentsHome.MENU:
                fragment = new MenuFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return FragmentsHome.COUNT;
    }

}
