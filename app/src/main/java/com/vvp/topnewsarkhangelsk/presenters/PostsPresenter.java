package com.vvp.topnewsarkhangelsk.presenters;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vvp.topnewsarkhangelsk.interfaces.PostInterface;
import com.vvp.topnewsarkhangelsk.repository.retrofit.RetrofitBuilder;
import com.vvp.topnewsarkhangelsk.repository.retrofit.pojo.POJO;
import com.vvp.topnewsarkhangelsk.repository.room.Post;
import com.vvp.topnewsarkhangelsk.utils.TextConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@InjectViewState
public class PostsPresenter extends MvpPresenter <PostInterface> {

    // массивы для постов
    static ArrayList <Post> arrayArkhLifePosts = new ArrayList<>();


    //конструктор
    public PostsPresenter() {

            loadData();
    }



    // загрузка данных
    void loadData(){


        Runnable runnable = () -> RetrofitBuilder.getInstance()
                .getRESTMethods()
                .getPostsFromArkhLife()
                .enqueue(new Callback<POJO>() {
                    @Override
                    public void onResponse(Call<POJO> call, Response<POJO> response) {

                        // ответ с сервера
                        POJO pojo = response.body();

                        for (int i = 0; i < 10; i++){

                            arrayArkhLifePosts.add(new Post(


                                    //____!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                    //сортировка по дате unixtime
                                    //pojo.getResponse().getItems().get(i).getDate();

                                    // конвертирование


                                    pojo.getResponse().getItems().get(i).getId(),
                                    pojo.getResponse().getItems().get(i).getOwnerId(),
                                    pojo.getResponse().getItems().get(i).getText(),
                                    pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                    pojo.getResponse().getItems().get(i).getComments().getCount()
                            ));

                        }

                        getViewState().showMessage("успешно!");

                        // сортировка
                        Collections.sort(arrayArkhLifePosts, Post.sortByLikes);




                        // получение текущей даты
                        Date date = new Date();

                        Log.i(TextConstants.TAG, "дата " + date.toString() );


                        //init RecyclerView
                        getViewState().initRecycclerViewArkhLife();
                    }

                    @Override
                    public void onFailure(Call<POJO> call, Throwable t) {

                        getViewState().showMessage("не удалось загрузить");
                    }
                });


        Thread thread = new Thread(runnable);
        thread.start();

    }


    public static ArrayList<Post> getArrayArkhLifePosts() {
        return arrayArkhLifePosts;
    }




}
