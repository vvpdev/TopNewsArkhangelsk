package com.vvp.topnewsarkhangelsk.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vvp.topnewsarkhangelsk.interfaces.RecViewPostsInterface;
import com.vvp.topnewsarkhangelsk.repository.retrofit.RetrofitBuilder;
import com.vvp.topnewsarkhangelsk.repository.retrofit.pojo.POJO;
import com.vvp.topnewsarkhangelsk.repository.room.Post;
import com.vvp.topnewsarkhangelsk.utils.SettingTime;
import com.vvp.topnewsarkhangelsk.utils.TextConstants;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@InjectViewState
public class RecViewPostPresenter extends MvpPresenter <RecViewPostsInterface> {


    // массив для постов
    private ArrayList<Post> arrayPosts = new ArrayList<>();


    // конструктор
    public RecViewPostPresenter() {

        getViewState().checkConnection();
    }


    public void loadData(boolean connection){

        if (connection){

            if (arrayPosts.isEmpty()){

                getViewState().showErrorOnTextView(false, ""); // скрываем TextView для ошибок
                getViewState().showProgressDialog(true);    // включаем показ прогресса

                // загрузка из первого паблика цепляет дальнейшую загрузку из других
                loadData1();
            }

            else {
                getViewState().initRecycclerView();
            }
        }

        else {

            if (arrayPosts.isEmpty()){

                getViewState().showErrorOnTextView(true, "Нет доступа к сети. Невозможно загрузить данные.");
                getViewState().showMessage("отсутствует подключение к сети");
            }

            else {
                getViewState().showErrorOnTextView(false, ""); // скрываем TextView для ошибок
                getViewState().showMessage("отсутствует подключение к сети");
                getViewState().initRecycclerView();
            }
        }
    }





    // Архангельск Life
    private void loadData1(){

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

                           // в диапазоне текущего дня
                            if ((checkDate > SettingTime.getFirstUnixTime()) & (checkDate < SettingTime.getSecondUnixTime())) {


                                if (pojo.getResponse().getItems().get(i).getAttachments() != null){

                                    if (pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto() != null ){

                                        if (pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto().getSizes() != null){

                                            if (pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto().getSizes().get(3) != null){

                                                arrayPosts.add(new Post(
                                                        pojo.getResponse().getItems().get(i).getId(),
                                                        setTitlePublic(pojo.getResponse().getItems().get(i).getOwnerId()),
                                                        pojo.getResponse().getItems().get(i).getText(),
                                                        pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto().getSizes().get(3).getUrl(),
                                                        pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                                        pojo.getResponse().getItems().get(i).getComments().getCount()
                                                ));
                                            }
                                        }
                                    }
                                }
                                else {
                                    arrayPosts.add(new Post(
                                            pojo.getResponse().getItems().get(i).getId(),
                                            setTitlePublic(pojo.getResponse().getItems().get(i).getOwnerId()),
                                            pojo.getResponse().getItems().get(i).getText(),
                                            "",
                                            pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                            pojo.getResponse().getItems().get(i).getComments().getCount()
                                    ));
                                }
                            }
                        }

                        // второй паблик
                        loadData2();
                    }

                    @Override
                    public void onFailure(Call<POJO> call, Throwable t) {

                    }
                });

        Thread thread = new Thread(runnable);
        thread.start();
    }


    // Подслушано Архангельск
    private void loadData2(){

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

                            // в диапазоне текущего дня
                            if ((checkDate > SettingTime.getFirstUnixTime()) & (checkDate < SettingTime.getSecondUnixTime())) {

                                if (pojo.getResponse().getItems().get(i).getAttachments() != null){

                                    if (pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto() != null ){

                                        if (pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto().getSizes() != null){

                                            if (pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto().getSizes().get(3) != null){

                                                arrayPosts.add(new Post(
                                                        pojo.getResponse().getItems().get(i).getId(),
                                                        setTitlePublic(pojo.getResponse().getItems().get(i).getOwnerId()),
                                                        pojo.getResponse().getItems().get(i).getText(),
                                                        pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto().getSizes().get(3).getUrl(),
                                                        pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                                        pojo.getResponse().getItems().get(i).getComments().getCount()
                                                ));
                                            }
                                        }
                                    }
                                }
                                else {
                                    arrayPosts.add(new Post(
                                            pojo.getResponse().getItems().get(i).getId(),
                                            setTitlePublic(pojo.getResponse().getItems().get(i).getOwnerId()),
                                            pojo.getResponse().getItems().get(i).getText(),
                                            "",
                                            pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                            pojo.getResponse().getItems().get(i).getComments().getCount()
                                    ));
                                }
                            }
                        }

                        loadData3();
                    }

                    @Override
                    public void onFailure(Call<POJO> call, Throwable t) {

                    }
                });

        Thread thread = new Thread(runnable);
        thread.start();
    }


    // 29.ru - новости Архангельска
    private void loadData3(){

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

                            // в диапазоне текущего дня
                            if ((checkDate > SettingTime.getFirstUnixTime()) & (checkDate < SettingTime.getSecondUnixTime())) {

                                if (pojo.getResponse().getItems().get(i).getAttachments() != null){

                                    if (pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto() != null ){

                                        if (pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto().getSizes() != null){

                                            if (pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto().getSizes().get(3) != null){

                                                arrayPosts.add(new Post(
                                                        pojo.getResponse().getItems().get(i).getId(),
                                                        setTitlePublic(pojo.getResponse().getItems().get(i).getOwnerId()),
                                                        pojo.getResponse().getItems().get(i).getText(),
                                                        pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto().getSizes().get(3).getUrl(),
                                                        pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                                        pojo.getResponse().getItems().get(i).getComments().getCount()
                                                ));
                                            }
                                        }
                                    }
                                }
                                else {
                                    arrayPosts.add(new Post(
                                            pojo.getResponse().getItems().get(i).getId(),
                                            setTitlePublic(pojo.getResponse().getItems().get(i).getOwnerId()),
                                            pojo.getResponse().getItems().get(i).getText(),
                                            "",
                                            pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                            pojo.getResponse().getItems().get(i).getComments().getCount()
                                    ));
                                }
                            }
                        }

                        loadData4();
                    }

                    @Override
                    public void onFailure(Call<POJO> call, Throwable t) {

                    }
                });

        Thread thread = new Thread(runnable);
        thread.start();
    }


    // Жесть Архангельска
    private void loadData4(){

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

                            // в диапазоне текущего дня
                            if ((checkDate > SettingTime.getFirstUnixTime()) & (checkDate < SettingTime.getSecondUnixTime())) {

                                if (pojo.getResponse().getItems().get(i).getAttachments() != null){

                                    if (pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto() != null ){

                                        if (pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto().getSizes() != null){

                                            if (pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto().getSizes().get(3) != null){

                                                arrayPosts.add(new Post(
                                                        pojo.getResponse().getItems().get(i).getId(),
                                                        setTitlePublic(pojo.getResponse().getItems().get(i).getOwnerId()),
                                                        pojo.getResponse().getItems().get(i).getText(),
                                                        pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto().getSizes().get(3).getUrl(),
                                                        pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                                        pojo.getResponse().getItems().get(i).getComments().getCount()
                                                ));
                                            }
                                        }
                                    }
                                }
                                else {
                                    arrayPosts.add(new Post(
                                            pojo.getResponse().getItems().get(i).getId(),
                                            setTitlePublic(pojo.getResponse().getItems().get(i).getOwnerId()),
                                            pojo.getResponse().getItems().get(i).getText(),
                                            "",
                                            pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                            pojo.getResponse().getItems().get(i).getComments().getCount()
                                    ));
                                }
                            }
                        }

                        loadData5();
                    }

                    @Override
                    public void onFailure(Call<POJO> call, Throwable t) {

                    }
                });

        Thread thread = new Thread(runnable);
        thread.start();
    }


    // Жесть Архангельска №2
    private void loadData5(){

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

                            // в диапазоне текущего дня
                            if ((checkDate > SettingTime.getFirstUnixTime()) & (checkDate < SettingTime.getSecondUnixTime())) {

                                if (pojo.getResponse().getItems().get(i).getAttachments() != null){

                                    if (pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto() != null ){

                                        if (pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto().getSizes() != null){

                                            if (pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto().getSizes().get(3) != null){

                                                arrayPosts.add(new Post(
                                                        pojo.getResponse().getItems().get(i).getId(),
                                                        setTitlePublic(pojo.getResponse().getItems().get(i).getOwnerId()),
                                                        pojo.getResponse().getItems().get(i).getText(),
                                                        pojo.getResponse().getItems().get(i).getAttachments().get(0).getPhoto().getSizes().get(3).getUrl(),
                                                        pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                                        pojo.getResponse().getItems().get(i).getComments().getCount()
                                                ));
                                            }
                                        }
                                    }
                                }
                                else {
                                    arrayPosts.add(new Post(
                                            pojo.getResponse().getItems().get(i).getId(),
                                            setTitlePublic(pojo.getResponse().getItems().get(i).getOwnerId()),
                                            pojo.getResponse().getItems().get(i).getText(),
                                            "",
                                            pojo.getResponse().getItems().get(i).getLikes().getCount(),
                                            pojo.getResponse().getItems().get(i).getComments().getCount()
                                    ));
                                }
                            }
                        }


                        // сортировка по лайкам
                        Collections.sort(arrayPosts, Post.sortByLikes);


                        getViewState().showProgressDialog(false);
                        getViewState().initRecycclerView();
                    }

                    @Override
                    public void onFailure(Call<POJO> call, Throwable t) {

                    }
                });

        Thread thread = new Thread(runnable);
        thread.start();
    }



    public ArrayList<Post> getArrayPosts() {
        return arrayPosts;
    }


    // вернуть заголовок паблика
    private String setTitlePublic(int idPublic){

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
