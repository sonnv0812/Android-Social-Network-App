package com.example.facebookapp.ui.home.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.facebookapp.R;
import com.example.facebookapp.data.model.post.Post;
import com.example.facebookapp.data.repository.home.newsfeed.NewsFeedRepository;
import com.example.facebookapp.data.repository.home.newsfeed.NewsFeedRepositoryImpl;
import com.example.facebookapp.listener.PostClickListener;
import com.example.facebookapp.ui.bottomsheet.PopupPostAction;
import com.example.facebookapp.ui.status.create.CreateStatusActivity;
import com.example.facebookapp.ui.home.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedFragment extends Fragment
        implements NewsFeedContract.View, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerHome;
    private PostAdapter adapter;
    private TextView textCreateStatus;
    private ImageView imageAvatar;
    private ActionBar actionBar;
    private NewsFeedContract.Presenter presenter;
    private String token, userId;
    private int count = 5;
    private List<Post> posts = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshHome;
    private boolean isLoading = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textCreateStatus = root.findViewById(R.id.text_status_home);
        imageAvatar = root.findViewById(R.id.image_avatar);
        initData();
        swipeRefreshHome = root.findViewById(R.id.swipe_refresh_home);
        recyclerHome = root.findViewById(R.id.recyclerview_home);
        recyclerHome.setAdapter(adapter);
        initPresenter();
        swipeRefreshHome.setOnRefreshListener(this);
        swipeRefreshHome.post(new Runnable() {
            @Override
            public void run() {
                if (swipeRefreshHome != null)
                    swipeRefreshHome.setRefreshing(true);

                refreshNewFeed();
            }
        });
        initScrollListener();
        return root;
    }

    private void initPresenter() {
        NewsFeedRepository repository = new NewsFeedRepositoryImpl();
        presenter = new NewsFeedPresenter(this, repository);
    }

    private void initData() {
        actionBar = ((HomeActivity) getActivity()).getSupportActionBar();
        actionBar.show();
        SharedPreferences dataAccountStorage = getContext().getSharedPreferences(getString(R.string.storage_data_account), Context.MODE_PRIVATE);
        token = dataAccountStorage.getString(getString(R.string.key_token), null);
        userId = dataAccountStorage.getString(getString(R.string.key_id), null);
        String avaLink = dataAccountStorage.getString(getString(R.string.key_avatar), null);
        Glide.with(getContext()).load(avaLink).into(imageAvatar);
        adapter = new PostAdapter(posts, new PostClickListener() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void onLikeClick(int position) {
                presenter.likePost(token, posts.get(position).getId(), position, posts.get(position).getLike());
            }

            @Override
            public void onActionClick(int position) {
                PopupPostAction popupPostAction = new PopupPostAction();
                popupPostAction.show(getFragmentManager(), popupPostAction.getTag());
            }
        });

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
    public void initNewsFeed(List<Post> posts) {
        adapter.updateData(posts);
        swipeRefreshHome.setRefreshing(false);
    }

    @Override
    public void showMessage(int msgResId) {
        Toast.makeText(getContext(), getString(msgResId), Toast.LENGTH_LONG).show();
        swipeRefreshHome.setRefreshing(false);
    }

    @Override
    public void loadMoreNews(List<Post> posts) {
        adapter.addData(posts);
        swipeRefreshHome.setRefreshing(false);
    }

    @Override
    public void updateLiked(int position, int like, int isLike) {
        Log.v("LIKE", like + " " + isLike);
        posts.get(position).setLike(like);
        posts.get(position).setIsLiked(isLike);
        adapter.notifyItemChanged(position);

        Log.v("LIKE", posts.get(position).getLike() + " " + posts.get(position).getIsLiked());
    }

    @Override
    public void onRefresh() {
        refreshNewFeed();
    }

    private void refreshNewFeed() {
        count = 5;
        posts.clear();
        presenter.requestGetPost(token, userId, null, 0, 5);
    }

    private void initScrollListener() {
        recyclerHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isLoading = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findFirstCompletelyVisibleItemPosition() == posts.size() - 1) {
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        posts.add(null);
        adapter.notifyItemInserted(posts.size() - 1);
        String lastId = posts.get(posts.size() - 1).getId();
        count += 5;
        presenter.requestGetPost(token, userId, lastId, posts.size(), count);
        isLoading = false;
    }

}
