package com.example.coursework.network

data class News(
   val articles: List<Articles>
)

data class Articles(
    val title: String,
    val url: String
)
