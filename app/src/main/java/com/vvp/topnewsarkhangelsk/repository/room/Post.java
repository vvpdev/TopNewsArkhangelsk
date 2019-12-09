package com.vvp.topnewsarkhangelsk.repository.room;


    // элемент для хранения в БД и отображения в RecyclerView

import java.io.Serializable;
import java.util.Comparator;

public class Post implements Serializable {


    int idPost;             // id поста

    int idCommunity;        // id сообщества/паблика

    String textPost;        // текст поста

    //String picture;         // url пикчи

    int quantityLikes;      // количество лайков

    int quantityComments;   // количество коментов

    //String link;            //ссылка на оригинал поста



    // конструктор
    public Post(int idPost, int idCommunity,  String textPost,  int quantityLikes, int quantityComments) {
        this.idPost = idPost;
        this.idCommunity = idCommunity;
        this.textPost = textPost;
       // this.picture = picture;
        this.quantityLikes = quantityLikes;
        this.quantityComments = quantityComments;
    }


    public int getIdPost() {
        return idPost;
    }

    public int getIdCommunity() {
        return idCommunity;
    }

    public String getTextPost() {
        return textPost;
    }

    public int getQuantityLikes() {
        return quantityLikes;
    }

    public int getQuantityComments() {
        return quantityComments;
    }

//    public String getPicture() {
//        return picture;
//    }




    // для сортировки
    public static final Comparator <Post> sortByLikes = (post1, post2) -> post2.getQuantityLikes() - post1.getQuantityLikes();




}
