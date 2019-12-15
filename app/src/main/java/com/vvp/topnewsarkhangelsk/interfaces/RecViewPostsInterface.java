package com.vvp.topnewsarkhangelsk.interfaces;

import com.arellomobile.mvp.MvpView;

public interface RecViewPostsInterface extends MvpView {

    // вывод сообщений через SnackBar
    void showMessage(String message);

     //если еще нет постов - пустой массив
    void showErrorOnTextView(boolean show, String message);

    //recyclerView
    void initRecycclerView();

    // прогресс загрузки
    void showProgressDialog(Boolean show);

}
