package com.example.roomdatabasekotlin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_user")
class User(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "user_full_name") val fullName: String
)





