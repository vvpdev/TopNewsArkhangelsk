package com.vvp.topnewsarkhangelsk.activities;

import android.os.Bundle;
import android.widget.LinearLayout;

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





        // активити для отображения постов

public class PostsActivity extends MvpAppCompatActivity implements PostInterface {


    @InjectPresenter
    PostsPresenter presenter;


    //UI
    RecyclerView recyclerViewArkhLife;
    RecyclerView.LayoutManager layoutManager;


    // для уведомления об ошибках
    Snackbar snackbarMessage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_activity);

        recyclerViewArkhLife = findViewById(R.id.recyclerViewArkhLife);

    }




    // сообщение об ошибке
    @Override
    public void showMessage(String message) {

        snackbarMessage = Snackbar.make(recyclerViewArkhLife, message, Snackbar.LENGTH_LONG)
                                .setAction("Action", null);
        snackbarMessage.show();

    }




    // настройка новостей из Архангельск Life
    @Override
    public void initRecycclerViewArkhLife() {

        // горизонтальный RecyclerView                  // строки
        layoutManager = new StaggeredGridLayoutManager(1, LinearLayout.HORIZONTAL);
        recyclerViewArkhLife.setLayoutManager(layoutManager);

        RecyclerViewPostsAdapter adapter = new RecyclerViewPostsAdapter(PostsPresenter.getArrayArkhLifePosts(), this);
        recyclerViewArkhLife.setAdapter(adapter);
    }

}
