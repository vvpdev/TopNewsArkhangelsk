package com.vvp.topnewsarkhangelsk.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.vvp.topnewsarkhangelsk.R;
import com.vvp.topnewsarkhangelsk.adapters.RecyclerViewPostsAdapter;
import com.vvp.topnewsarkhangelsk.interfaces.PostInterface;
import com.vvp.topnewsarkhangelsk.presenters.PostsPresenter;


// app для сбора постов с пабликов Архангельска и показа самых топовых по лайкам, репостам и коментам

        // кеш через Room (очитска кеша????) - через кнопку в настройках
        // меню настройки -  цветовая схема
        // вывод в шторке
        // Retrofit
        // сортировка по текущей дате
        // прогресс бар загрузки




        // активити для отображения постов

public class PostsActivity extends MvpAppCompatActivity implements PostInterface {


    @InjectPresenter
    PostsPresenter presenter;

    //UI
    RecyclerView recyclerViewArkhLife;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressLoad;


    // для уведомлений
    Snackbar snackbarMessage;
    TextView textError;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_activity);

        recyclerViewArkhLife = findViewById(R.id.recyclerViewArkhLife);
        textError = findViewById(R.id.textError);
        progressLoad = findViewById(R.id.progressLoad);

    }



    // сообщение об ошибке
    @Override
    public void showMessage(String message) {

        snackbarMessage = Snackbar.make(recyclerViewArkhLife, message, Snackbar.LENGTH_LONG)
                                .setAction("Action", null);
        snackbarMessage.show();
    }


    // ошибка - пустой массив (еще нет постов за текущий день)
    @Override
    public void showEmpryArrayError(String message) {

        textError.setVisibility(View.VISIBLE);
        textError.setText(message);
    }


    // оборажение постов в RecyclerView
    @Override
    public void initRecycclerView() {

        textError.setVisibility(View.GONE);

        layoutManager = new LinearLayoutManager(this);
        recyclerViewArkhLife.setLayoutManager(layoutManager);

        RecyclerViewPostsAdapter adapter = new RecyclerViewPostsAdapter(PostsPresenter.getArrayPosts(), this);
        recyclerViewArkhLife.setAdapter(adapter);
    }


    // прогресс бар
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
