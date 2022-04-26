package com.example.roomdatabasekotlin.repositories

import android.app.Application
import com.example.roomdatabasekotlin.database.UserDao
import com.example.roomdatabasekotlin.managers.RoomManager
import com.example.roomdatabasekotlin.models.Note
import com.example.roomdatabasekotlin.models.User

open class UserRepository(application: Application) {
    private var userDao: UserDao

    init {
        val db = RoomManager.getInstance(application)
        userDao = db.userDao()
    }

    fun getUsers(): List<User> {
        return userDao.getUsers()
    }

    fun saveUser(user: User): Long {
        return userDao.saveUser(user)
    }

    fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}
