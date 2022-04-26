package com.example.roomdatabasekotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasekotlin.databinding.ItemNoteBinding
import com.example.roomdatabasekotlin.models.Note

class NoteAdapter(private val context: Context, private val list: ArrayList<Note>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val noteBinding = ItemNoteBinding.inflate(LayoutInflater.from(context), parent, false)
        return NoteViewHolder(noteBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val note = list[position]

        if (holder is NoteViewHolder) {
            val noteViewHolder = (holder as NoteViewHolder)

            noteViewHolder.textViewDate.text = note.date.toString()
            noteViewHolder.textViewNoteTitle.text = note.title.toString()
        }
    }

    override fun getItemCount() = list.size

    private class NoteViewHolder(noteBinding: ItemNoteBinding) :
        RecyclerView.ViewHolder(noteBinding.root) {
        val textViewDate: TextView = noteBinding.textViewDate
        val textViewNoteTitle: TextView = noteBinding.textViewNoteTitle
    }
}

