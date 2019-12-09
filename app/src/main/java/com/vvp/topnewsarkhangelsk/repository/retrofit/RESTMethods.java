package com.vvp.topnewsarkhangelsk.repository.retrofit;

import com.vvp.topnewsarkhangelsk.repository.retrofit.pojo.POJO;
import com.vvp.topnewsarkhangelsk.utils.TextConstants;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RESTMethods {


    // паблик Архангельск life
    @GET("/method/wall.get?owner_id=-127267864&count=10&access_token=ff3685a6ff3685a6ff3685a659ff58f3d2fff36ff3685a6a2dba3ed2104ba4d4e4ac74f&v=5.103")
    Call<POJO> getPostsFromArkhLife();

}
