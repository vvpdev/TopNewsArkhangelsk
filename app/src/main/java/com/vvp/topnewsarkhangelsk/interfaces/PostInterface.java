package com.vvp.topnewsarkhangelsk.interfaces;

import android.view.View;

import com.arellomobile.mvp.MvpView;

public interface PostInterface extends MvpView {


    // вывод сообщений через SnackBar
    void showMessage(String message);


    //recyclerView для Архангельск Life
    void initRecycclerViewArkhLife();

}
