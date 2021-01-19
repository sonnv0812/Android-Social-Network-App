package com.example.facebookapp.ui.profile.my;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.facebookapp.R;
import com.example.facebookapp.ui.profile.setup.SetupProfileActivity;

public class MyProfileActivity extends AppCompatActivity
        implements MyProfileContract.View {

    private TextView textName;
    private ImageView imageAvatar;
    private ImageButton buttonSetupProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textName = findViewById(R.id.text_username);
        imageAvatar = findViewById(R.id.image_avatar);
        buttonSetupProfile = findViewById(R.id.button_setup_profile);
        initUI();
    }

    private void initUI() {
        String username;
        SharedPreferences dataAccountStorage = getSharedPreferences(getString(R.string.storage_data_account), Context.MODE_PRIVATE);
        username = dataAccountStorage.getString(getString(R.string.key_username), null);
        textName.setText(username);
        String avatarLink = dataAccountStorage.getString(getString(R.string.key_avatar), null);
        Glide.with(this).load(avatarLink).into(imageAvatar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(username);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        buttonSetupProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this, SetupProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            default:
                break;
        }
        return true;
    }
}
