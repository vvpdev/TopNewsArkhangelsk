package com.vvp.topnewsarkhangelsk.repository.retrofit;


    // инициализация Retrofit

import com.vvp.topnewsarkhangelsk.utils.TextConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

        // переменная класса
        private static RetrofitBuilder instance;

        // переменная ретрофита
        private Retrofit retrofit;


        // конструктор класса
    private RetrofitBuilder() {

            // init
            retrofit = new Retrofit.Builder()
                                    .baseUrl(TextConstants.BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
    }


    // синглтон
    public static RetrofitBuilder getInstance() {

        if (instance == null){
            instance = new RetrofitBuilder();
        }
        return instance;
    }


    // возврат анонимного класса на основе интерфейса
    public RESTMethods getRESTMethods(){

        return retrofit.create(RESTMethods.class);
    }


}
