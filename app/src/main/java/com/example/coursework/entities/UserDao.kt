package com.example.coursework.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE login = :login")
    fun listLogin(login: String): List<User>

    @Query("SELECT * FROM user WHERE login = :login AND password = :password")
    fun listUser(login: String, password: String): List<User>


}