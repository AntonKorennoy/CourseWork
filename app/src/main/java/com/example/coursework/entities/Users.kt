package com.example.coursework.entities

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1
)
abstract class Users :RoomDatabase(){

    abstract fun getUserDao():UserDao

}