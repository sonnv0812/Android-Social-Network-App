package com.example.facebookapp.ui.friend.suggest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.facebookapp.R;
import com.example.facebookapp.data.repository.friend.suggest.SuggestRepository;
import com.example.facebookapp.data.repository.friend.suggest.SuggestRepositoryImpl;

public class SuggestFriendActivity extends AppCompatActivity implements SuggestFriendContract.View {

    private SuggestFriendContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_friend);

        initPresenter();
    }

    private void initPresenter() {
        SuggestRepository repository = new SuggestRepositoryImpl();
        presenter = new SuggestFriendPresenter(this, repository);
    }
}