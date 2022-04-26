package com.example.roomdatabasekotlin.managers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabasekotlin.database.NoteDao
import com.example.roomdatabasekotlin.database.UserDao
import com.example.roomdatabasekotlin.models.Note
import com.example.roomdatabasekotlin.models.User


@Database(entities = [User::class, Note::class], version = 2, exportSchema = false)
abstract class RoomManager : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun noteDao(): NoteDao

    companion object {

        @Volatile
        private var INSTANCE: RoomManager? = null

        fun getInstance(context: Context): RoomManager {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RoomManager::class.java, "app_db"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE!!
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}


