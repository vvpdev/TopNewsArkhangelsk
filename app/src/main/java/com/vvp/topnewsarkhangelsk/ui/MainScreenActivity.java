package com.vvp.topnewsarkhangelsk.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.navigation.NavigationView;
import com.vvp.topnewsarkhangelsk.R;
import com.vvp.topnewsarkhangelsk.interfaces.MainScreenInterface;
import com.vvp.topnewsarkhangelsk.presenters.MainScreenPresenter;
import com.vvp.topnewsarkhangelsk.presenters.RecViewPostPresenter;
import com.vvp.topnewsarkhangelsk.utils.TextConstants;

import java.util.Objects;


// app для сбора постов с пабликов Архангельска и показа самых топовых по лайкам, репостам и коментам

        // кеш через Room (очитска кеша????) - через кнопку в настройках
        // меню настройки -  цветовая схема
        // вывод в шторке
        // Retrofit
        // сортировка по текущей дате
        // прогресс бар загрузки


public class MainScreenActivity extends MvpAppCompatActivity implements MainScreenInterface,  NavigationView.OnNavigationItemSelectedListener {

    @InjectPresenter
    MainScreenPresenter presenter;

    //управление макетом
    DrawerLayout drawerLayout;
    NavigationView navigationViewPosts;
    Toolbar toolbar;

    // переключатель для боковой шторки
    ActionBarDrawerToggle toggle;

    //фрагменты
    FragmentManager fragmentManager;

    // фрагменты
    RecyclerViewFragment recyclerViewFragment;
    SettingFragment settingFragment;
    AboutAppFragment aboutAppFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_view);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //для шторки
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationViewPosts = findViewById(R.id.navigationViewPosts);
    }





    // выбор для боковой шторки
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        settingFragment = new SettingFragment();
        aboutAppFragment = new AboutAppFragment();

        switch (menuItem.getItemId()){

            case R.id.postsAction:
                transaction.replace(R.id.frameLayoutContent, recyclerViewFragment);
                break;

            case R.id.settingAction:
                transaction.replace(R.id.frameLayoutContent, settingFragment);
                break;

            case R.id.aboutAppAction:
                transaction.replace(R.id.frameLayoutContent, aboutAppFragment);
                break;
        }

        // transaction.addToBackStack(null);   // добавить в стек переходов
        transaction.commit();

        drawerLayout.closeDrawer(GravityCompat.START);      //закрыть шторку

        return true;
    }


    //
    @Override
    public void initialSettingScreen() {


        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true); // индикатор - кнопка гамбургер
        toggle.syncState();

        navigationViewPosts.bringToFront();
        navigationViewPosts.setNavigationItemSelectedListener(this);

        // первоначальная настройка
        recyclerViewFragment = new RecyclerViewFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.frameLayoutContent, recyclerViewFragment)
                .commit();
    }




}
