package com.example.roomdatabasekotlin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id") var id: Long? = null,
    @ColumnInfo(name = "note_date") var date: String? = null,
    @ColumnInfo(name = "note_title") var title: String? = null,
    @ColumnInfo(name = "note_description") var description: String? = null
)


