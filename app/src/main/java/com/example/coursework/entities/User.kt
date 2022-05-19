package com.example.coursework.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    @NonNull
    val login: String,

    @NonNull
    val password: String
)