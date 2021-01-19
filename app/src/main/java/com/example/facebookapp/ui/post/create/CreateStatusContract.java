package com.example.facebookapp.ui.post.create;

import java.io.File;
import java.util.List;

public interface CreateStatusContract {
    interface View {
        void showError(int msgResId);

        void successfulAddPost();
    }

    interface Presenter {
        void handlePost(String token,
                        String described,
                        String status,
                        List<File> image,
                        File video);
    }
}
