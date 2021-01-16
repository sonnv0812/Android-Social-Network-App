package com.example.facebookapp.ui.status.create;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.facebookapp.R;
import com.example.facebookapp.data.repository.status.create.CreateStatusRepository;
import com.example.facebookapp.data.repository.status.create.CreateStatusRepositoryImpl;
import com.example.facebookapp.ui.status.create.bottomsheet.PopupBackCreateStatus;
import com.example.facebookapp.ui.home.activity.HomeActivity;
import com.squareup.picasso.Picasso;

public class CreateStatusActivity extends AppCompatActivity implements CreateStatusContract.View {

    private EditText editPost;
    private CreateStatusContract.Presenter presenter;
    private String described;
    private SharedPreferences dataAccountStorage;
    private ProgressDialog dialog;
    private ImageView imageAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_status);

        dataAccountStorage = getSharedPreferences(getString(R.string.storage_data_account), Context.MODE_PRIVATE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.actionbar_create_status);
        actionBar.setDisplayHomeAsUpEnabled(true);
        editPost = findViewById(R.id.edit_post);
        imageAvatar = findViewById(R.id.image_avatar);
        initPresenter();
        initUI();

        dialog = new ProgressDialog(this);
        dialog.setTitle("Đợi chút");
    }

    private void initPresenter() {
        CreateStatusRepository repository = new CreateStatusRepositoryImpl();
        presenter = new CreateStatusPresenter(this, repository);
    }

    private void initUI() {
        String avatarLink = dataAccountStorage.getString(getString(R.string.key_avatar), null);
        Picasso.get().load(avatarLink).into(imageAvatar);
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
                described = s.toString();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_create_status, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                PopupBackCreateStatus popupBackCreateStatus = new PopupBackCreateStatus();
                popupBackCreateStatus.show(getSupportFragmentManager(), popupBackCreateStatus.getTag());
                break;
            case R.id.button_create:
                dialog.show();
                Log.v("CREATE", "Event selected");
                String token = dataAccountStorage.getString(getString(R.string.key_token), null);
                presenter.handlePost(token, described, null, null, null);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void showError(int msgResId) {
        dialog.dismiss();
        Toast.makeText(this, getString(msgResId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void successfulAddPost() {
        dialog.dismiss();
        Toast.makeText(this, getString(R.string.notify_add_post_successful), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(CreateStatusActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}