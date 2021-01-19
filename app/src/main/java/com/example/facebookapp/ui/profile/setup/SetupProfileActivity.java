package com.example.facebookapp.ui.profile.setup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.facebookapp.R;
import com.example.facebookapp.ui.profile.edit.EditProfileActivity;

public class SetupProfileActivity extends AppCompatActivity {

    private ConstraintLayout constraintEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.actionbar_setup_profile));
        actionBar.setDisplayHomeAsUpEnabled(true);
        constraintEditProfile = findViewById(R.id.constraint_edit_profile);
    }

    @Override
    protected void onResume() {
        super.onResume();
        constraintEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetupProfileActivity.this, EditProfileActivity.class);
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