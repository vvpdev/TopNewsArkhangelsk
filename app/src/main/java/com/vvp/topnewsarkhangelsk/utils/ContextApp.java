package com.vvp.topnewsarkhangelsk.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


// глобальный Context для работы с Room

public class ContextApp extends Application {


    // экземпляр контекста
    private static ContextApp ourInstance;

    // для настроек и состояния сети
    public static SharedPreferences sharedPreferences;



    // создаем
    @Override
    public void onCreate() {
        super.onCreate();

        ourInstance = (ContextApp) getApplicationContext();

        sharedPreferences = this.getSharedPreferences("sp_vvp", Context.MODE_PRIVATE);
    }


    // геттер для инстанса
    public static ContextApp getInstance() {

        return ourInstance;
    }



}
