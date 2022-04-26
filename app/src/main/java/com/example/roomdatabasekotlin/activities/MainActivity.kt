package com.example.roomdatabasekotlin.activities

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasekotlin.adapters.NoteAdapter
import com.example.roomdatabasekotlin.databinding.ActivityMainBinding
import com.example.roomdatabasekotlin.databinding.CustomDialogViewBinding
import com.example.roomdatabasekotlin.models.Note
import com.example.roomdatabasekotlin.repositories.NoteRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var context: Context
    private lateinit var recyclerView: RecyclerView
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var notesList: ArrayList<Note>
    private lateinit var repository: NoteRepository

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViews() {
        context = this
        repository = NoteRepository(application)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        floatingActionButton = binding.fabAdd

       // notesList = arrayListOf()

        noteAdapter = NoteAdapter(context, notesList)
        recyclerView.adapter = noteAdapter


        floatingActionButton.setOnClickListener {
            showAlertDialog()
        }

    }

    private fun noteExecutor(repository: NoteRepository, note: Note) {
        val executor = Executors.newSingleThreadExecutor() //in background
        val handler = Handler(Looper.getMainLooper()) //in UI
        executor.execute {
            val size = repository.saveNote(note)
            val list = repository.getNotes() as ArrayList<Note>
            handler.post {
                // UI
                notesList = list
                noteAdapter.notifyItemInserted(size.toInt())
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun showAlertDialog() {

        val alertDialog = android.app.AlertDialog.Builder(context)
        alertDialog.setTitle("New Note")

        val dialogViewBinding =
            CustomDialogViewBinding.inflate(LayoutInflater.from(context), null, false)
        alertDialog.setView(dialogViewBinding.root)

        alertDialog.setPositiveButton("Save") { _, _ ->

            val title = dialogViewBinding.edittextTitle.text.toString()
            val description = dialogViewBinding.edittextNote.text.toString()
            val note = Note(null, now(), title, description)

            noteExecutor(repository, note)
        }
        alertDialog.setNegativeButton("Cancel") { _, _ -> }

        alertDialog.create().show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun now(): String {
        val today = LocalDateTime.now()
        return today.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS"))
    }
}


