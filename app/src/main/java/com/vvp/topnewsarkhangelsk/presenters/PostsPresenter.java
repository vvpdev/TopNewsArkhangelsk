package com.vvp.topnewsarkhangelsk.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vvp.topnewsarkhangelsk.interfaces.PostInterface;
import com.vvp.topnewsarkhangelsk.repository.retrofit.RetrofitBuilder;
import com.vvp.topnewsarkhangelsk.repository.retrofit.pojo.POJO;
import com.vvp.topnewsarkhangelsk.repository.room.Post;
import com.vvp.topnewsarkhangelsk.utils.SettingTime;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@InjectViewState
public class PostsPresenter extends MvpPresenter <PostInterface> {

    // массив для постов
    static ArrayList <Post> arrayPosts = new ArrayList<>();


    //конструктор
    public PostsPresenter() {

        loadData1();
    }


    // Архангельск Life
    void loadData1(){

        // показать ПрогрессБар
        getViewState().showProgressDialog(true);

        Runnable runnable = () -> RetrofitBuilder.getInstance()
                .getRESTMethods()
                .getPostsFromArkhLife()
                .enqueue(new Callback<POJO>() {
                    @Override
                    public void onResponse(Call<POJO> call, Response<POJO> response) {

                        // ответ с сервера
                        POJO pojo = response.body();

                        for (int i = 0; i < 10; i++){

                            // время публикации поста
                            long checkDate = pojo.getResponse().getItems().get(i).getDate();

//                            // в диапазоне текущего дня
                            if ((checkDate > SettingTime.getFirstUnixTime()) & (checkDate < SettingTime.getSecondUnixTime())) {

                                arrayPosts.add(new Post(
                                        pojo.getResponse().getItems().get(i).getId(),
                                        setTitlePublic(pojo.getResponse().getItems().get(i).getOwnerId()),
                                        pojo.getResponse().getItems().get(i).getText(),
                                        pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                        pojo.getResponse().getItems().get(i).getComments().getCount()
                                ));
                            }
                        }

                        // второй паблик
                        loadData2();
                    }

                    @Override
                    public void onFailure(Call<POJO> call, Throwable t) {

                        getViewState().showMessage("не удалось загрузить");
                    }
                });

        Thread thread = new Thread(runnable);
        thread.start();
    }



    // Подслушано Архангельск
    void loadData2(){

        Runnable runnable = () -> RetrofitBuilder.getInstance()
                .getRESTMethods()
                .getPostsFromPodslushArkh()
                .enqueue(new Callback<POJO>() {
                    @Override
                    public void onResponse(Call<POJO> call, Response<POJO> response) {

                        // ответ с сервера
                        POJO pojo = response.body();

                        for (int i = 0; i < 10; i++){

                            // время публикации поста
                            long checkDate = pojo.getResponse().getItems().get(i).getDate();

//                            // в диапазоне текущего дня
                            if ((checkDate > SettingTime.getFirstUnixTime()) & (checkDate < SettingTime.getSecondUnixTime())) {

                                arrayPosts.add(new Post(
                                        pojo.getResponse().getItems().get(i).getId(),
                                        setTitlePublic(pojo.getResponse().getItems().get(i).getOwnerId()),
                                        pojo.getResponse().getItems().get(i).getText(),
                                        pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                        pojo.getResponse().getItems().get(i).getComments().getCount()
                                ));
                            }
                        }
                        // второй паблик
                        loadData3();
                    }

                    @Override
                    public void onFailure(Call<POJO> call, Throwable t) {

                        getViewState().showMessage("не удалось загрузить");
                    }
                });

        Thread thread = new Thread(runnable);
        thread.start();
    }




    // 29.ru - новости Архангельска
    void loadData3(){

        Runnable runnable = () -> RetrofitBuilder.getInstance()
                .getRESTMethods()
                .getPostsFrom29RU()
                .enqueue(new Callback<POJO>() {
                    @Override
                    public void onResponse(Call<POJO> call, Response<POJO> response) {

                        // ответ с сервера
                        POJO pojo = response.body();

                        for (int i = 0; i < 10; i++){

                            // время публикации поста
                            long checkDate = pojo.getResponse().getItems().get(i).getDate();

//                            // в диапазоне текущего дня
                            if ((checkDate > SettingTime.getFirstUnixTime()) & (checkDate < SettingTime.getSecondUnixTime())) {

                                arrayPosts.add(new Post(
                                        pojo.getResponse().getItems().get(i).getId(),
                                        setTitlePublic(pojo.getResponse().getItems().get(i).getOwnerId()),
                                        pojo.getResponse().getItems().get(i).getText(),
                                        pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                        pojo.getResponse().getItems().get(i).getComments().getCount()
                                ));
                            }
                        }

                        loadData4();
                    }

                    @Override
                    public void onFailure(Call<POJO> call, Throwable t) {

                        getViewState().showMessage("не удалось загрузить");
                    }
                });

        Thread thread = new Thread(runnable);
        thread.start();
    }



    // Жесть Архангельска
    void loadData4(){

        Runnable runnable = () -> RetrofitBuilder.getInstance()
                .getRESTMethods()
                .getPostsFromJest29()
                .enqueue(new Callback<POJO>() {
                    @Override
                    public void onResponse(Call<POJO> call, Response<POJO> response) {

                        // ответ с сервера
                        POJO pojo = response.body();

                        for (int i = 0; i < 10; i++){

                            // время публикации поста
                            long checkDate = pojo.getResponse().getItems().get(i).getDate();

//                            // в диапазоне текущего дня
                            if ((checkDate > SettingTime.getFirstUnixTime()) & (checkDate < SettingTime.getSecondUnixTime())) {

                                arrayPosts.add(new Post(
                                        pojo.getResponse().getItems().get(i).getId(),
                                        setTitlePublic(pojo.getResponse().getItems().get(i).getOwnerId()),
                                        pojo.getResponse().getItems().get(i).getText(),
                                        pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                        pojo.getResponse().getItems().get(i).getComments().getCount()
                                ));
                            }
                        }

                        loadData5();
                    }

                    @Override
                    public void onFailure(Call<POJO> call, Throwable t) {

                        getViewState().showMessage("не удалось загрузить");
                    }
                });

        Thread thread = new Thread(runnable);
        thread.start();
    }




    // Жесть Архангельска №2
    void loadData5(){

        Runnable runnable = () -> RetrofitBuilder.getInstance()
                .getRESTMethods()
                .getPostsFromGhest29()
                .enqueue(new Callback<POJO>() {
                    @Override
                    public void onResponse(Call<POJO> call, Response<POJO> response) {

                        // ответ с сервера
                        POJO pojo = response.body();

                        for (int i = 0; i < 10; i++){

                            // время публикации поста
                            long checkDate = pojo.getResponse().getItems().get(i).getDate();

//                            // в диапазоне текущего дня
                            if ((checkDate > SettingTime.getFirstUnixTime()) & (checkDate < SettingTime.getSecondUnixTime())) {

                                arrayPosts.add(new Post(
                                        pojo.getResponse().getItems().get(i).getId(),
                                        setTitlePublic(pojo.getResponse().getItems().get(i).getOwnerId()),
                                        pojo.getResponse().getItems().get(i).getText(),
                                        pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                        pojo.getResponse().getItems().get(i).getComments().getCount()
                                ));
                            }
                        }


                        // если вообще нет постов
                        if (arrayPosts.isEmpty()){
                            getViewState().showEmpryArrayError("еще нет постов");
                        }
                        else {

                            // сортировка по лайкам
                            Collections.sort(arrayPosts, Post.sortByLikes);

                            // скрыть прогресс бар
                            getViewState().showProgressDialog(false);

                            //init RecyclerView
                            getViewState().initRecycclerView();
                        }
                    }

                    @Override
                    public void onFailure(Call<POJO> call, Throwable t) {

                        getViewState().showMessage("не удалось загрузить");
                    }
                });

        Thread thread = new Thread(runnable);
        thread.start();
    }





    public static ArrayList<Post> getArrayPosts() {
        return arrayPosts;
    }



    // вернуть заголовок паблика
    String setTitlePublic(int idPublic){

        String title = "";

        switch (idPublic){

            case -127267864:
                title = "Архангельск life";
                return title;

            case -36958793:
                title = "Подслушано Архангельск";
                return title;

            case -29725798:
                title = "29.ru Новости Архангельска";
                return title;

            case -56468672:
                title = "Жесть Архангельска";
                return title;

            case -10564356:
                title = "Жесть Поморья: Архангельск Северодвинск регион";
                return title;
        }

        return title;
    }




}
