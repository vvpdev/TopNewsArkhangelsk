package com.vvp.topnewsarkhangelsk.Activities;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vvp.topnewsarkhangelsk.Presenters.PostsPresenter;
import com.vvp.topnewsarkhangelsk.R;


        // app для сбора постов с пабликов Архангельска и показа самых топовых по лайкам, репостам и коментам

        // кеш через Room (очитска кеша????) - через кнопку в настройках
        // как кешировать картинки ????
        // меню настройки - уведомления, частота обновления, цветовая схема
        // уведомления о новых постах в топе
        // запросы через Services, пока приложение не работает
        // вывод в шторке
        // Retrofit




        // активити для отображения постов

public class PostsActivity extends MvpAppCompatActivity {


    @InjectPresenter
    PostsPresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_activity);






    }
}
