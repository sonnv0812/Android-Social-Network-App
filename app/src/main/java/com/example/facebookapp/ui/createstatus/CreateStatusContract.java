package com.example.facebookapp.ui.createstatus;

import java.util.List;

public interface CreateStatusContract {
    interface View {
        void showError(int msgResId);

        void successfulAddPost();
    }

    interface Presenter {
        void handlePost(String token,
                        List<String> image,
                        String video,
                        String described,
                        String status);
    }
}
