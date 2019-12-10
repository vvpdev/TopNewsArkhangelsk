package com.vvp.topnewsarkhangelsk.repository.retrofit;

import com.vvp.topnewsarkhangelsk.repository.retrofit.pojo.POJO;
import com.vvp.topnewsarkhangelsk.utils.TextConstants;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RESTMethods {


    // Архангельск life
    @GET("/method/wall.get?owner_id=-127267864&count=50&access_token=ff3685a6ff3685a6ff3685a659ff58f3d2fff36ff3685a6a2dba3ed2104ba4d4e4ac74f&v=5.103")
    Call<POJO> getPostsFromArkhLife();


    // Подслушано Архангельск
    @GET("/method/wall.get?owner_id=-36958793&count=50&access_token=ff3685a6ff3685a6ff3685a659ff58f3d2fff36ff3685a6a2dba3ed2104ba4d4e4ac74f&v=5.103")
    Call<POJO> getPostsFromPodslushArkh();

    //29.ru - новости Архангельска
    @GET("/method/wall.get?owner_id=-29725798&count=50&access_token=ff3685a6ff3685a6ff3685a659ff58f3d2fff36ff3685a6a2dba3ed2104ba4d4e4ac74f&v=5.103")
    Call<POJO> getPostsFrom29RU();


    // Жесть Архангельска
    @GET("/method/wall.get?owner_id=-56468672&count=50&access_token=ff3685a6ff3685a6ff3685a659ff58f3d2fff36ff3685a6a2dba3ed2104ba4d4e4ac74f&v=5.103")
    Call<POJO> getPostsFromJest29();


    //Жесть Архангельска №2
    @GET("/method/wall.get?owner_id=-10564356&count=50&access_token=ff3685a6ff3685a6ff3685a659ff58f3d2fff36ff3685a6a2dba3ed2104ba4d4e4ac74f&v=5.103")
    Call<POJO> getPostsFromGhest29();


}
