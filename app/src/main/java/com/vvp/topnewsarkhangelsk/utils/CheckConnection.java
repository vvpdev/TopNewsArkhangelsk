package com.vvp.topnewsarkhangelsk.utils;


    // слушатель для проверки инета

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Objects;

public class CheckConnection extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //  слушатель системных сообщений об инете
        final String BROADCAST_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

                String action = intent.getAction();

                // если есть изменение состояния сети
                if (BROADCAST_ACTION.equals(action)) {

                    isNetworkAvaliable();
                }
            }



    // проверка инета
    public static boolean isNetworkAvaliable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) ContextApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();

        if (networkInfo != null && Objects.requireNonNull(networkInfo).isConnected()){
            return true;
        }

        else {
            return false;
        }
    }



    }

