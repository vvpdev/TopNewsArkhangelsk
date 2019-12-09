package com.vvp.topnewsarkhangelsk.utils;

import android.app.Application;


    // глобальный Context для работы с Room

public class ContextApp extends Application {


    // экземпляр контекста
    private static ContextApp ourInstance;


    // создаем
    @Override
    public void onCreate() {
        super.onCreate();

        ourInstance = (ContextApp) getApplicationContext();
    }


    // геттер для инстанса
    public static ContextApp getInstance() {

        return ourInstance;
    }

}
