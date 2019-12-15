package com.vvp.topnewsarkhangelsk.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vvp.topnewsarkhangelsk.interfaces.MainScreenInterface;
import com.vvp.topnewsarkhangelsk.repository.room.Post;

import java.util.ArrayList;


@InjectViewState
public class MainScreenPresenter extends MvpPresenter <MainScreenInterface> {


    public MainScreenPresenter() {

       getViewState().initialSettingScreen();

    }


}
