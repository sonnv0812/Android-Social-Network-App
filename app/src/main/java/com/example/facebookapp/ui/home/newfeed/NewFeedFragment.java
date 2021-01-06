package com.example.facebookapp.ui.home.newfeed;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapp.R;

public class NewFeedFragment extends Fragment {
    private RecyclerView recyclerHome;
    private PostAdapter postAdapter = new PostAdapter();
    TextView textStatusHome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerHome = root.findViewById(R.id.recyclerview_home);
        recyclerHome.setAdapter(postAdapter);
        textStatusHome = root.findViewById(R.id.text_status_home);

        textStatusHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }
}
