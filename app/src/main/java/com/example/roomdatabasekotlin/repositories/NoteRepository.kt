package com.example.roomdatabasekotlin.repositories

import android.app.Application
import com.example.roomdatabasekotlin.database.NoteDao
import com.example.roomdatabasekotlin.managers.RoomManager
import com.example.roomdatabasekotlin.models.Note

open class NoteRepository(application: Application) {
    private var noteDao: NoteDao

    init {
        val db = RoomManager.getInstance(application)
        noteDao = db.noteDao()
    }

    fun getNotes(): List<Note> {
        return noteDao.getNotes()
    }

    fun saveNote(note: Note): Long {
        return noteDao.saveNote(note)
    }

    fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}
