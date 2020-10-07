package com.example.facebookapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapp.R;
import com.example.facebookapp.adapter.PostAdapter;
import com.example.facebookapp.model.PostModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerHome;
    private PostAdapter postAdapter = new PostAdapter();

    public HomeFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerHome = view.findViewById(R.id.recyclerview_home);
        recyclerHome.setAdapter(postAdapter);
        initData();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initData() {
        List<PostModel> posts = new ArrayList<>();

        posts.add(new PostModel(
                "https://wallpapercave.com/wp/k4cxWQK.jpg",
                "Nguyen Van Son",
                2,
                "Hai lớp B và C cùng kế thừa từ một lớp cha là lớp A, giả sử B và C overriding phương thức của lớp A (overriding tức là B và C không những cùng kế thừa một phương thức nào đó từ lớp A mà còn sửa lại phương thức đã kế thừa đó tùy thuộc vào B hoặc C). Khi D tiếp tục kế thừa phương thức này từ B và C, D không biết sẽ chọn chọn phương thức của lớp B hay của phương thức của C để kế thừa.",
                30,
                false,
                false
        ));

        posts.add(new PostModel(
                "https://wallpapercave.com/wp/k4cxWQK.jpg",
                "Nguyen Van Son",
                2,
                "Hai lớp B và C cùng kế thừa từ một lớp cha là lớp A, giả sử B và C overriding phương thức của lớp A (overriding tức là B và C không những cùng kế thừa một phương thức nào đó từ lớp A mà còn sửa lại phương thức đã kế thừa đó tùy thuộc vào B hoặc C). Khi D tiếp tục kế thừa phương thức này từ B và C, D không biết sẽ chọn chọn phương thức của lớp B hay của phương thức của C để kế thừa.",
                30,
                false,
                false
        ));

        posts.add(new PostModel(
                "https://wallpapercave.com/wp/k4cxWQK.jpg",
                "Nguyen Van Son",
                2,
                "Hai lớp B và C cùng kế thừa từ một lớp cha là lớp A, giả sử B và C overriding phương thức của lớp A (overriding tức là B và C không những cùng kế thừa một phương thức nào đó từ lớp A mà còn sửa lại phương thức đã kế thừa đó tùy thuộc vào B hoặc C). Khi D tiếp tục kế thừa phương thức này từ B và C, D không biết sẽ chọn chọn phương thức của lớp B hay của phương thức của C để kế thừa.",
                30,
                false,
                false
        ));

        posts.add(new PostModel(
                "https://wallpapercave.com/wp/k4cxWQK.jpg",
                "Nguyen Van Son",
                2,
                "Hai lớp B và C cùng kế thừa từ một lớp cha là lớp A, giả sử B và C overriding phương thức của lớp A (overriding tức là B và C không những cùng kế thừa một phương thức nào đó từ lớp A mà còn sửa lại phương thức đã kế thừa đó tùy thuộc vào B hoặc C). Khi D tiếp tục kế thừa phương thức này từ B và C, D không biết sẽ chọn chọn phương thức của lớp B hay của phương thức của C để kế thừa.",
                30,
                false,
                false
        ));

        postAdapter.addData(posts);
    }
}
