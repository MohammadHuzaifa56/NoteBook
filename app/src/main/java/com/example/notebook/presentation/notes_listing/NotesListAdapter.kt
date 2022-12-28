package com.example.notebook.presentation.notes_listing

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook.R
import com.example.notebook.databinding.NotesItemBinding
import com.example.notebook.domain.enum.MoodStates
import com.example.notebook.domain.model.NotesItemEntity
import com.example.notebook.presentation.notes_detail.NotesDetailActivity
import dagger.hilt.android.qualifiers.ActivityContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NotesListAdapter @Inject constructor(@ActivityContext val context: Context) :
    RecyclerView.Adapter<NotesListAdapter.NotesViewHolder>() {
    private var notesList: List<NotesItemEntity> = arrayListOf()
    class NotesViewHolder(itemView: View, val binding: NotesItemBinding) : RecyclerView.ViewHolder(itemView) {

    }

    fun setNotesList(notesList: List<NotesItemEntity>) {
        this.notesList = notesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val notesItemEntity = notesList[position]
        holder.binding.tvNotesContent.text = notesItemEntity.text
        holder.binding.tvDate.text = getFormattedDate(Date(notesItemEntity.date))
        holder.binding.tvMoodState.apply {
            text = notesItemEntity.mood
            setTextColor(getMoodStateColor(notesItemEntity.mood))
        }

        holder.itemView.setOnClickListener{
            val intent = Intent(context,NotesDetailActivity::class.java)
            intent.putExtra("noteId", notesItemEntity.id)
            context.startActivity(intent)
        }
    }

    fun getMoodStateColor(mood: String): Int {
        return when (mood) {
            MoodStates.GoodDay.state -> context.getColor(R.color.green)
            MoodStates.BadDay.state -> context.getColor(R.color.red)
            MoodStates.NormalDay.state -> context.getColor(R.color.yellow)
            else -> context.getColor(R.color.green)
        }
    }

    fun getFormattedDate(date: Date): String? {
        val simpleDateTimeFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)
        return simpleDateTimeFormat.format(date)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}