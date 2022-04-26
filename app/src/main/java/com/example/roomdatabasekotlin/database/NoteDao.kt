package com.example.roomdatabasekotlin.database

import androidx.room.*
import com.example.roomdatabasekotlin.models.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveNote(note: Note): Long

    @Query("SELECT * FROM notes_table")
    fun getNotes(): List<Note>

    @Delete
    fun deleteNote(note: Note)
}