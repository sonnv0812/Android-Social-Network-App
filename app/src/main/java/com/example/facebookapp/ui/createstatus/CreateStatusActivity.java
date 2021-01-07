package com.example.facebookapp.ui.createstatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.facebookapp.R;
import com.example.facebookapp.ui.createstatus.bottomsheet.BottomSheetFragment;

public class CreateStatusActivity extends AppCompatActivity implements CreateStatusContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_status);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.actionBar_create_status);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                break;

            default:
                break;
        }
        return true;
    }
}