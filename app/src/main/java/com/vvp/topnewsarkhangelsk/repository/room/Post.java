package com.vvp.topnewsarkhangelsk.repository.room;


    // элемент для хранения в БД и отображения в RecyclerView

import java.io.Serializable;
import java.util.Comparator;

public class Post implements Serializable {


    int idPost;             // id поста

    String titlePublic;     // заголовок паблика

    String textPost;        // текст поста

    int quantityLikes;      // количество лайков

    int quantityComments;   // количество коментов



    // конструктор
    public Post(int idPost, String titlePublic, String textPost,  int quantityLikes, int quantityComments) {
        this.idPost = idPost;
        this.titlePublic = titlePublic;
        this.textPost = textPost;
        this.quantityLikes = quantityLikes;
        this.quantityComments = quantityComments;
    }


    public int getIdPost() {
        return idPost;
    }

    public String getTitlePublic() {
        return titlePublic;
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





    // для сортировки
    public static final Comparator <Post> sortByLikes = (post1, post2) -> post2.getQuantityLikes() - post1.getQuantityLikes();




}
