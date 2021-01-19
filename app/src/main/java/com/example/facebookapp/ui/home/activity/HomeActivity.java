package com.example.facebookapp.ui.home.activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.example.facebookapp.R;
import com.example.facebookapp.config.FragmentHome;
import com.example.facebookapp.data.model.account.AccountModel;
import com.example.facebookapp.data.repository.home.home.HomeRepository;
import com.example.facebookapp.data.repository.home.home.HomeRepositoryImpl;
import com.example.facebookapp.ui.login.LoginActivity;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private SharedPreferences dataAccountStorage;
    private ViewPager viewPager;
    private PagerHomeAdapter pagerHomeAdapter;
    private HomeContract.Presenter presenter;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dataAccountStorage = getSharedPreferences(getString(R.string.storage_data_account), Context.MODE_PRIVATE);
        pagerHomeAdapter = new PagerHomeAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager = findViewById(R.id.view_pager_home);
        viewPager.setAdapter(pagerHomeAdapter);
        initPresenter();
        updateDataAccount();
        TabLayout tabLayout = findViewById(R.id.tab_layout_home);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(FragmentHome.HOME).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(FragmentHome.VIDEO).setIcon(R.drawable.ic_video);
        tabLayout.getTabAt(FragmentHome.NOTIFICATION).setIcon(R.drawable.ic_notifications);
        tabLayout.getTabAt(FragmentHome.FRIEND).setIcon(R.drawable.ic_friend);
        tabLayout.getTabAt(FragmentHome.MENU).setIcon(R.drawable.ic_menu);
        String checkToken = dataAccountStorage.getString(getString(R.string.key_token), null);

        presenter.checkToken(checkToken);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    private void updateDataAccount() {
        Bundle receive = getIntent().getExtras();
        if (receive != null) {
            String id = receive.getString(getString(R.string.key_id));
            String token = receive.getString(getString(R.string.key_token));
            String avatarLink = receive.getString(getString(R.string.key_avatar));
            Log.v("AVATAR", avatarLink);
            Log.v("TOKEN", token);
            dataAccountStorage.edit().putString(getString(R.string.key_token), token).apply();
            dataAccountStorage.edit().putString(getString(R.string.key_id), id).apply();
            dataAccountStorage.edit().putString(getString(R.string.key_avatar), avatarLink).apply();
            presenter.getInfo(token, id);
        }
    }

    private void initPresenter() {
        HomeRepository repository = new HomeRepositoryImpl();
        presenter = new HomePresenter(this, repository);
    }

    @Override
    public void returnLogin() {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void saveUserInfo(@NotNull AccountModel account) {
        dataAccountStorage.edit().putString(getString(R.string.key_username), account.getUsername()).apply();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
        }
    }
}
