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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapp.R;
import com.example.facebookapp.ui.createstatus.CreateStatusActivity;
import com.squareup.picasso.Picasso;

public class NewFeedFragment extends Fragment {
    private RecyclerView recyclerHome;
    private PostAdapter postAdapter = new PostAdapter();
    private TextView textCreateStatus;
    private ImageView imageAvatar;
    private SharedPreferences dataAccountStorage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerHome = root.findViewById(R.id.recyclerview_home);
        recyclerHome.setAdapter(postAdapter);
        textCreateStatus = root.findViewById(R.id.text_status_home);
        imageAvatar = root.findViewById(R.id.image_avatar);
        dataAccountStorage =
                getContext().getSharedPreferences(getString(R.string.storage_data_account), Context.MODE_PRIVATE);
        initData();
        return root;
    }

    private void initData() {
        String avaLink = dataAccountStorage.getString(getString(R.string.key_avatar), null);
        Picasso.get().load(avaLink).into(imageAvatar);
    }

    @Override
    public void onResume() {
        super.onResume();

        textCreateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateStatusActivity.class);
                startActivity(intent);
            }
        });
    }
}
