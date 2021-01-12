package com.example.facebookapp.ui.friend.all;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.facebookapp.R;
import com.example.facebookapp.listener.FriendUserClickListener;
import com.example.facebookapp.data.model.friend.Friend;
import com.example.facebookapp.data.repository.friend.all.AllFriendRepository;
import com.example.facebookapp.data.repository.friend.all.AllFriendRepositoryImpl;
import com.example.facebookapp.ui.friend.all.bottomsheet.BottomSheetSetupFragment;

import java.util.List;

public class AllFriendActivity extends AppCompatActivity implements AllFriendContract.View {

    private String token, userId;
    private SharedPreferences dataAccountStorage;
    private AllFriendContract.Presenter presenter;
    private AllFriendAdapter adapter;
    private RecyclerView recyclerUserFriend;
    private List<Friend> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_friend);

        dataAccountStorage = getSharedPreferences(getString(R.string.storage_data_account), Context.MODE_PRIVATE);
        token = dataAccountStorage.getString(getString(R.string.key_token), null);
        userId = dataAccountStorage.getString(getString(R.string.key_id), null);

        recyclerUserFriend = findViewById(R.id.recyclerview_all_friend);
        initPresenter();
        presenter.handleGetUserFriend(token, userId);

        adapter = new AllFriendAdapter(friendList, new FriendUserClickListener() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void onSetupClick(int position) {
                BottomSheetSetupFragment bottomSheetSetupFragment = new BottomSheetSetupFragment();
                bottomSheetSetupFragment.show(getSupportFragmentManager(), bottomSheetSetupFragment.getTag());
            }
        });

        recyclerUserFriend.setAdapter(adapter);
    }

    private void initPresenter() {
        AllFriendRepository repository = new AllFriendRepositoryImpl();
        presenter = new AllFriendPresenter(this, repository);
    }

    @Override
    public void updateFriendData(List<Friend> friendList) {
        adapter.updateData(friendList);
    }

    @Override
    public void showMessage(int msgResId) {
        Toast.makeText(this, getString(msgResId), Toast.LENGTH_LONG).show();
    }
}
