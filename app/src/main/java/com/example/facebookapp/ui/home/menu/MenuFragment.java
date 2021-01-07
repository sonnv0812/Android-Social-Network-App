package com.example.facebookapp.ui.home.menu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.facebookapp.R;
import com.example.facebookapp.data.repository.home.menu.MenuRepository;
import com.example.facebookapp.data.repository.home.menu.MenuRepositoryImpl;
import com.example.facebookapp.ui.login.LoginActivity;

public class MenuFragment extends Fragment implements MenuContract.View {

    private ConstraintLayout logout;
    private String token;
    private MenuContract.Presenter presenter;
    private SharedPreferences dataAccountStorage;
    private ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container,false);
        logout = root.findViewById(R.id.container_logout);
        dataAccountStorage =
                getContext().getSharedPreferences(getString(R.string.storage_data_account), Context.MODE_PRIVATE);
        readData();
        initPresenter();
        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Đợi chút");
        return root;
    }

    private void readData() {
        token = dataAccountStorage.getString(getString(R.string.key_token), null);
        Log.v("TOKEN", token);
    }

    private void initPresenter() {
        MenuRepository repository = new MenuRepositoryImpl();
        presenter = new MenuPresenter(this, repository);
    }

    @Override
    public void onResume() {
        super.onResume();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                presenter.handleLogout(token);
            }
        });
    }

    @Override
    public void showError(int msgResId) {
        dialog.dismiss();
        Toast.makeText(getContext(), getString(msgResId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void successLogout() {
        dataAccountStorage.edit().putString(getString(R.string.key_token), null).apply();
        dialog.dismiss();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
}
