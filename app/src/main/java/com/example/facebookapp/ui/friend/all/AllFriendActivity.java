package com.example.facebookapp.ui.friend.all;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.facebookapp.R;
import com.example.facebookapp.data.repository.friend.all.AllFriendRepository;
import com.example.facebookapp.data.repository.friend.all.AllFriendRepositoryImpl;

public class AllFriendActivity extends AppCompatActivity implements AllFriendContract.View {

    private AllFriendContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_friend);

        initPresenter();
    }

    private void initPresenter() {
        AllFriendRepository repository = new AllFriendRepositoryImpl();
        presenter = new AllFriendPresenter(this, repository);
    }
}