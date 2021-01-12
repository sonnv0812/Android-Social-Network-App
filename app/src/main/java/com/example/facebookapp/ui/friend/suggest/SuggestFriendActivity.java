package com.example.facebookapp.ui.friend.suggest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.facebookapp.R;
import com.example.facebookapp.data.model.friend.Friend;
import com.example.facebookapp.data.repository.friend.suggest.SuggestRepository;
import com.example.facebookapp.data.repository.friend.suggest.SuggestRepositoryImpl;
import com.example.facebookapp.listener.FriendSuggestClickListener;

import java.util.List;

import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

public class SuggestFriendActivity extends AppCompatActivity implements SuggestFriendContract.View {

    private String token;
    private SuggestFriendContract.Presenter presenter;
    private RecyclerView recyclerSuggestFriend;
    private SuggestFriendAdapter adapter;
    private List<Friend> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_friend);

        SharedPreferences dataAccountStorage = getSharedPreferences(getString(R.string.storage_data_account), Context.MODE_PRIVATE);
        token = dataAccountStorage.getString(getString(R.string.key_token), null);

        initPresenter();
        presenter.handlerGetSuggestList(token);

        recyclerSuggestFriend = findViewById(R.id.recyclerview_suggest_friend);
        adapter = new SuggestFriendAdapter(friendList, new FriendSuggestClickListener() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void onAddClick(int position) {

            }

            @Override
            public void onRemoveClick(int position) {
                friendList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        recyclerSuggestFriend.setAdapter(adapter);
        recyclerSuggestFriend.setItemAnimator(new ScaleInAnimator());
    }

    private void initPresenter() {
        SuggestRepository repository = new SuggestRepositoryImpl();
        presenter = new SuggestFriendPresenter(this, repository);
    }

    @Override
    public void updateSuggestList(List<Friend> friendList) {
        adapter.updateData(friendList);
    }

    @Override
    public void showMessage(int msgResId) {
        Toast.makeText(this, getString(msgResId), Toast.LENGTH_LONG).show();
    }
}
