package com.example.facebookapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.facebookapp.fragment.GroupFragment;
import com.example.facebookapp.fragment.HomeFragment;
import com.example.facebookapp.fragment.MenuAccountFragment;
import com.example.facebookapp.fragment.NotificationFragment;
import com.example.facebookapp.fragment.VideoFragment;

public class PagerHomeAdapter extends FragmentPagerAdapter {

    public PagerHomeAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case Fragments.HOME:
                fragment = new HomeFragment();
                break;
            case Fragments.VIDEO:
                fragment = new VideoFragment();
                break;
            case Fragments.NOTIFICATION:
                fragment = new NotificationFragment();
                break;
            case Fragments.GROUP:
                fragment = new GroupFragment();
                break;
            case Fragments.MENU:
                fragment = new MenuAccountFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return Fragments.COUNT;
    }

}
