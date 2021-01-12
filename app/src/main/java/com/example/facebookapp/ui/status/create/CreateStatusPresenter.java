package com.example.facebookapp.ui.status.create;

import com.example.facebookapp.data.base.OnDataLoadedListener;
import com.example.facebookapp.data.repository.status.create.CreateStatusRepository;
import com.example.facebookapp.R;

import java.util.List;

public class CreateStatusPresenter implements CreateStatusContract.Presenter {

    private CreateStatusContract.View view;
    private CreateStatusRepository repository;

    public CreateStatusPresenter(CreateStatusContract.View view, CreateStatusRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void handlePost(String token, List<String> image, String video, String described, String status) {
        if (described == null)
            view.showError(R.string.error_null_input);
        else
            repository.getApiAddPost(token, image, video, described, status, new OnDataLoadedListener<String>() {
                @Override
                public void onSuccess(String data) {
                    view.successfulAddPost();
                }

                @Override
                public void onFailure(Exception exception) {
                    view.showError(Integer.parseInt(exception.getMessage()));
                }
            });
    }
}
