package com.example.coursework.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetWorkGoogleNews {

    companion object {

        @JvmStatic
        var baseUrl = "https://newsapi.org/v2/"

        @JvmStatic
        var key = "530f68b9b003497eab4bd27e141bf727"

        @JvmStatic
        fun getNewWork(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}