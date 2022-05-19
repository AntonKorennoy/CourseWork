package com.example.coursework.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LinksNews {

    @GET("top-headlines?")
    fun getNews(
        @Query("country") country: String = "ru",
        @Query("apiKey") key: String = NetWorkGoogleNews.key,
        @Query("page") page: Int = 1,
        @Query("pageSize") size: Int = 5
    ): Call<News>

}