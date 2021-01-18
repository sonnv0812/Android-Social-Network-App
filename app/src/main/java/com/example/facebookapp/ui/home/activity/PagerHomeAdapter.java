package com.example.facebookapp.ui.home.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.facebookapp.config.FragmentHome;
import com.example.facebookapp.ui.home.friend.FriendFragment;
import com.example.facebookapp.ui.home.newsfeed.NewsFeedFragment;
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
            case FragmentHome.HOME:
                fragment = new NewsFeedFragment();
                break;
            case FragmentHome.VIDEO:
                fragment = new VideoFragment();
                break;
            case FragmentHome.NOTIFICATION:
                fragment = new NotificationFragment();
                break;
            case FragmentHome.FRIEND:
                fragment = new FriendFragment();
                break;
            case FragmentHome.MENU:
                fragment = new MenuFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return FragmentHome.COUNT;
    }

}
