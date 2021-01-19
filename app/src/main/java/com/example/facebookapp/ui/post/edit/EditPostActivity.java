package com.example.facebookapp.ui.post.edit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.facebookapp.R;
import com.example.facebookapp.ui.bottomsheet.PopupBackCreateStatus;
import com.example.facebookapp.ui.dialog.BackEditPostDialog;
import com.example.facebookapp.ui.profile.edit.EditProfileContract;

public class EditPostActivity extends AppCompatActivity implements EditProfileContract.View {

    private ImageView imageAvatar;
    private TextView textUsername;
    private EditText editPost;
    private String userId, token, username, avatarLink;
    private String describedAfter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        imageAvatar = findViewById(R.id.image_avatar);
        textUsername = findViewById(R.id.text_username);
        editPost = findViewById(R.id.edit_edit_post);
        initUI();
    }

    private void initUI() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chỉnh sửa bài viết");
        actionBar.setDisplayHomeAsUpEnabled(true);

        SharedPreferences dataAccountStorage = getSharedPreferences(getString(R.string.storage_data_account), Context.MODE_PRIVATE);
        userId = dataAccountStorage.getString(getString(R.string.key_id), null);
        token = dataAccountStorage.getString(getString(R.string.key_token), null);
        username = dataAccountStorage.getString(getString(R.string.key_username), null);
        avatarLink = dataAccountStorage.getString(getString(R.string.key_avatar), null);

        Glide.with(this).load(avatarLink).into(imageAvatar);
        textUsername.setText(username);
        String beforeDesc = getIntent().getExtras().getString("described");
        editPost.setText(beforeDesc);
    }

    @Override
    protected void onResume() {
        super.onResume();
        editPost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_edit_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                BackEditPostDialog backEditPostDialog = new BackEditPostDialog();
                backEditPostDialog.show(getSupportFragmentManager(), backEditPostDialog.getTag());
                break;
            case R.id.button_save:
                break;
        }
        return true;
    }
}
