package com.vvp.topnewsarkhangelsk.interfaces;

import android.view.View;

import com.arellomobile.mvp.MvpView;

public interface PostInterface extends MvpView {


    // вывод сообщений через SnackBar
    void showMessage(String message);

    // если еще нет постов - пустой массив
    void showEmpryArrayError(String message);

    //recyclerView
    void initRecycclerView();

    // прогресс загрузки
    void showProgressDialog(Boolean show);

}
