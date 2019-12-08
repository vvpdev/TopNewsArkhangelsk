package com.vvp.topnewsarkhangelsk.Repository.Retrofit;


    // инициализация Retrofit

import com.vvp.topnewsarkhangelsk.Utils.TextConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

        // переменная класса
        private static RetrofitBuilder instance;

        // переменная ретрофита
        private Retrofit retrofit;


        // конструктор класса
    public RetrofitBuilder() {

            // init
            retrofit = new Retrofit.Builder()
                                    .baseUrl(TextConstants.BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
    }


    // геттер для инстанса ретрофита
    public static RetrofitBuilder getInstance() {
        return instance;
    }





}
