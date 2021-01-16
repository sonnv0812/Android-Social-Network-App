package com.example.facebookapp.ui.home.newfeed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.facebookapp.R;
import com.example.facebookapp.data.model.post.Post;
import com.example.facebookapp.data.repository.home.newfeed.NewFeedRepository;
import com.example.facebookapp.data.repository.home.newfeed.NewFeedRepositoryImpl;
import com.example.facebookapp.listener.PostClickListener;
import com.example.facebookapp.ui.status.create.CreateStatusActivity;
import com.example.facebookapp.ui.home.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class NewFeedFragment extends Fragment implements NewFeedContract.View {

    private RecyclerView recyclerHome;
    private PostAdapter adapter;
    private TextView textCreateStatus;
    private ImageView imageAvatar;
    private SharedPreferences dataAccountStorage;
    private ActionBar actionBar;
    private NewFeedContract.Presenter presenter;
    private String token, userId;
    private int count = 5;
    private List<Post> posts = new ArrayList<>();
    private PostClickListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        actionBar = ((HomeActivity) getActivity()).getSupportActionBar();
        actionBar.show();
        textCreateStatus = root.findViewById(R.id.text_status_home);
        imageAvatar = root.findViewById(R.id.image_avatar);
        dataAccountStorage =
                getContext().getSharedPreferences(getString(R.string.storage_data_account), Context.MODE_PRIVATE);
        token = dataAccountStorage.getString(getString(R.string.key_token), null);
        userId = dataAccountStorage.getString(getString(R.string.key_id), null);
        initData();
        recyclerHome = root.findViewById(R.id.recyclerview_home);
        recyclerHome.setAdapter(adapter);

        initPresenter();

        presenter.requestGetPost(token, userId, null, 0, count);
        return root;
    }

    private void initPresenter() {
        NewFeedRepository repository = new NewFeedRepositoryImpl();
        presenter = new NewFeedPresenter(this, repository);
    }

    private void initData() {
        String avaLink = dataAccountStorage.getString(getString(R.string.key_avatar), null);
        Glide.with(getContext()).load(avaLink).into(imageAvatar);
        adapter = new PostAdapter(recyclerHome, posts, listener);
    }

    @Override
    public void onResume() {
        super.onResume();
        actionBar.show();
        textCreateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateStatusActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void updateNewFeed(List<Post> posts) {
        adapter.updateData(posts);
    }

    @Override
    public void showMessage(int msgResId) {

    }
}
