package com.example.notebook.presentation.notes_listing

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook.R
import com.example.notebook.databinding.GroupedNotesItemsBinding
import com.example.notebook.domain.enum.MoodStates
import com.example.notebook.domain.model.MonthGroup
import com.example.notebook.presentation.notes_detail.NotesDetailActivity
import dagger.hilt.android.qualifiers.ActivityContext
import java.util.*
import javax.inject.Inject

class GroupedNotesListAdapter @Inject constructor(@ActivityContext private val context: Context) :
    RecyclerView.Adapter<GroupedNotesListAdapter.NotesViewHolder>() {
    private var notesList: List<MonthGroup> = arrayListOf()
    @Inject
    lateinit var dateItemsAdapter: DateItemsAdapter
    var totalNotes = 0;
    class NotesViewHolder(itemView: View, val binding: GroupedNotesItemsBinding) : RecyclerView.ViewHolder(itemView) {

    }

    fun setNotesList(notesList: List<MonthGroup>) {
        this.notesList = notesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = GroupedNotesItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val notesItemEntity = notesList[position]
        holder.binding.tvMonthName.text = notesItemEntity.month
        holder.binding.layMonthHeading.setBackgroundColor(getAvgMoodColor(notesItemEntity.avgMood))
        holder.binding.tvTotalEntries.text = "${notesItemEntity.totalEntries} Entries"
        dateItemsAdapter.setDateItems(notesItemEntity.dateGroups)
        holder.binding.recDates.adapter = dateItemsAdapter
    }

    private fun getAvgMoodColor(mood: Int): Int {
        return when (mood) {
            MoodStates.GoodDay.state -> context.getColor(R.color.green)
            MoodStates.BadDay.state -> context.getColor(R.color.red)
            MoodStates.NormalDay.state -> context.getColor(R.color.yellow)
            else -> context.getColor(R.color.green)
        }
    }
    override fun getItemCount(): Int {
        return notesList.size
    }
}