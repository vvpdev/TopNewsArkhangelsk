package com.vvp.topnewsarkhangelsk.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.vvp.topnewsarkhangelsk.R;
import com.vvp.topnewsarkhangelsk.adapters.RecyclerViewPostsAdapter;
import com.vvp.topnewsarkhangelsk.interfaces.RecViewPostsInterface;
import com.vvp.topnewsarkhangelsk.presenters.RecViewPostPresenter;
import com.vvp.topnewsarkhangelsk.utils.ContextApp;

public class RecyclerViewFragment extends MvpAppCompatFragment implements RecViewPostsInterface {




    @InjectPresenter
    RecViewPostPresenter presenter;


    //UI
    private RecyclerView recyclerViewPosts;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressLoad;


    // для уведомлений
    private Snackbar snackbarMessage;
    private TextView textError;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewPosts = view.findViewById(R.id.recyclerViewPosts);
        textError = view.findViewById(R.id.textError);
        textError.setVisibility(View.GONE);       // пока скрываем
        progressLoad = view.findViewById(R.id.progressLoad);
        progressLoad.setVisibility(View.GONE);    // пока скрываем

    }




    // вывод сообщений через snackabr
    @Override
    public void showMessage(String message) {

        snackbarMessage = Snackbar.make(recyclerViewPosts, message, Snackbar.LENGTH_LONG)
                                .setAction("Action", null);
        snackbarMessage.show();
    }


    // текст ошибки на TextView
    @Override
    public void showErrorOnTextView(boolean show, String message) {

        if (show){
            textError.setVisibility(View.VISIBLE);
            textError.setText(message);
        }

        else {
            textError.setVisibility(View.GONE);
        }
    }



    @Override
    public void initRecycclerView() {

        textError.setVisibility(View.GONE);

        layoutManager = new LinearLayoutManager(ContextApp.getInstance());
        recyclerViewPosts.setLayoutManager(layoutManager);

        RecyclerViewPostsAdapter adapter = new RecyclerViewPostsAdapter(RecViewPostPresenter.getArrayPosts(), ContextApp.getInstance());
        recyclerViewPosts.setAdapter(adapter);
    }


    // управление прогресс баром
    @Override
    public void showProgressDialog(Boolean show) {

        if (show){
            progressLoad.setVisibility(View.VISIBLE);
        }
        else {
            progressLoad.setVisibility(View.GONE);
        }
    }

}
