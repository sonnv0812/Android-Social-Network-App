package com.example.facebookapp.ui.home.activity;


import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;

import com.example.facebookapp.R;
import com.example.facebookapp.config.FragmentsHome;
import com.example.facebookapp.ui.home.activity.PagerHomeAdapter;
import com.example.facebookapp.ui.login.LoginActivity;
import com.google.android.material.tabs.TabLayout;


public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    
    private final SharedPreferences tokenStorage = getSharedPreferences("TOKEN_STORAGE", Context.MODE_PRIVATE);
    private ViewPager viewPager;
    private PagerHomeAdapter pagerHomeAdapter;
    private HomeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pagerHomeAdapter = new PagerHomeAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager = findViewById(R.id.view_pager_home);
        viewPager.setAdapter(pagerHomeAdapter);
        initPresenter();

        TabLayout tabLayout = findViewById(R.id.tab_layout_home);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(FragmentsHome.HOME).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(FragmentsHome.VIDEO).setIcon(R.drawable.ic_video);
        tabLayout.getTabAt(FragmentsHome.NOTIFICATION).setIcon(R.drawable.ic_notifications);
        tabLayout.getTabAt(FragmentsHome.GROUP).setIcon(R.drawable.ic_group);
        tabLayout.getTabAt(FragmentsHome.MENU).setIcon(R.drawable.ic_menu);
        String checkToken = tokenStorage.getString("token", null);
        presenter.checkToken(checkToken);
        updateToken();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    private void updateToken() {
        Bundle receive = getIntent().getExtras();
        if (receive != null) {
            String token = receive.getString("token");
            @SuppressLint("CommitPrefEdits")
            SharedPreferences.Editor editor = tokenStorage.edit();
            editor.putString("token", token);
        }
    }

    private void initPresenter() {
        presenter = new HomePresenter(this);
    }

    @Override
    public void returnLogin() {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
