package com.example.facebookapp.ui.home.friend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapp.R;
import com.example.facebookapp.config.FriendOnClickListener;
import com.example.facebookapp.data.model.friend.Friend;
import com.example.facebookapp.data.repository.home.friend.FriendRepository;
import com.example.facebookapp.data.repository.home.friend.FriendRepositoryImpl;
import com.example.facebookapp.ui.friend.all.AllFriendActivity;
import com.example.facebookapp.ui.friend.suggest.SuggestFriendActivity;
import com.example.facebookapp.ui.home.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

public class FriendFragment extends Fragment implements FriendContract.View {

    private String token;
    private SharedPreferences dataAccountStorage;
    private Button buttonSuggestFriend, buttonAllFriend;
    private FriendContract.Presenter presenter;
    private RecyclerView recyclerRequest;
    private FriendRequestAdapter adapter;
    private List<Friend> friends = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_friend, container, false);

        dataAccountStorage =
                getContext().getSharedPreferences(getString(R.string.storage_data_account), Context.MODE_PRIVATE);

        buttonSuggestFriend = root.findViewById(R.id.button_suggest_friend);
        buttonAllFriend = root.findViewById(R.id.button_all_friend);
        recyclerRequest = root.findViewById(R.id.recyclerview_request_friend);
        token = dataAccountStorage.getString(getString(R.string.key_token), null);

        initPresenter();
        presenter.handleRequestFriend(token);
        adapter = new FriendRequestAdapter(friends, new FriendOnClickListener() {
            @Override
            public void onAcceptClicked(int position) {
                Log.v("FRIEND", "Accept is clicked " + position);
                presenter.handleAcceptFriend(token, friends.get(position).getId(), true, position);
            }

            @Override
            public void onDeleteClicked(int position) {
                Log.v("FRIEND", "Delete is clicked " + position);
                presenter.handleAcceptFriend(token, friends.get(position).getId(), false, position);
            }

            @Override
            public void onClicked(int position) {

            }
        });

        recyclerRequest.setAdapter(adapter);
        recyclerRequest.setItemAnimator(new ScaleInAnimator());
        return root;
    }

    private void initPresenter() {
        FriendRepository repository = new FriendRepositoryImpl();
        presenter = new FriendPresenter(this, repository);
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = ((HomeActivity) getActivity()).getSupportActionBar();
        actionBar.hide();
        buttonSuggestFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SuggestFriendActivity.class);
                startActivity(intent);
            }
        });

        buttonAllFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AllFriendActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void updateData(List<Friend> friendRequestList) {
        adapter.updateData(friendRequestList);
    }

    @Override
    public void showMessage(int msgResId) {
        Toast.makeText(getContext(), getString(msgResId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateUIAfterAccept(String userId, int position) {
        friends.remove(position);
        adapter.notifyItemRemoved(position);
    }
}
