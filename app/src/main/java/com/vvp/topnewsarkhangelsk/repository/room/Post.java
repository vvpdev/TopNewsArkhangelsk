package com.vvp.topnewsarkhangelsk.repository.room;


    // элемент для хранения в БД и отображения в RecyclerView

import java.io.Serializable;
import java.util.Comparator;

public class Post implements Serializable {


    private int idPost;             // id поста

    private String titlePublic;     // заголовок паблика

    private String textPost;        // текст поста

    private String picture;         // картинка

    private int quantityLikes;      // количество лайков

    private int quantityComments;   // количество коментов



    // конструктор
    public Post(int idPost, String titlePublic, String textPost, String picture, int quantityLikes, int quantityComments) {
        this.idPost = idPost;
        this.titlePublic = titlePublic;
        this.textPost = textPost;
        this.picture = picture;
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

    public String getPicture() {
        return picture;
    }

    // для сортировки
    public static final Comparator <Post> sortByLikes = (post1, post2) -> post2.getQuantityLikes() - post1.getQuantityLikes();




}
