package com.example.roomdatabasekotlin.database

import androidx.room.*
import com.example.roomdatabasekotlin.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveUser(user: User): Long

    @Query("SELECT * FROM table_user")
    fun getUsers(): List<User>

    @Delete
    fun deleteUser(user: User)
}

