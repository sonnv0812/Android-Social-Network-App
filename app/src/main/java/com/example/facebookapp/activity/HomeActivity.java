package com.example.facebookapp.activity;


import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;

import com.example.facebookapp.R;
import com.example.facebookapp.adapter.Fragments;
import com.example.facebookapp.adapter.PagerHomeAdapter;
import com.google.android.material.tabs.TabLayout;


public class HomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerHomeAdapter pagerHomeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pagerHomeAdapter = new PagerHomeAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPager = findViewById(R.id.view_pager_home);
        viewPager.setAdapter(pagerHomeAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout_home);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(Fragments.HOME).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(Fragments.VIDEO).setIcon(R.drawable.ic_video);
        tabLayout.getTabAt(Fragments.NOTIFICATION).setIcon(R.drawable.ic_notifications);
        tabLayout.getTabAt(Fragments.GROUP).setIcon(R.drawable.ic_group);
        tabLayout.getTabAt(Fragments.MENU).setIcon(R.drawable.ic_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

}