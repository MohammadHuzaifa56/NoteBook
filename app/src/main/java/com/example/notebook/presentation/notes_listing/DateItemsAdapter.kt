package com.example.notebook.presentation.notes_listing

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook.R
import com.example.notebook.databinding.DatesItemBinding
import com.example.notebook.databinding.GroupedNotesItemsBinding
import com.example.notebook.databinding.NotesItemBinding
import com.example.notebook.domain.enum.MoodStates
import com.example.notebook.domain.model.DateGroup
import com.example.notebook.domain.model.MonthGroup
import com.example.notebook.domain.model.NotesItemEntity
import com.example.notebook.presentation.notes_detail.NotesDetailActivity
import com.example.notebook.utils.AppHelper
import dagger.hilt.android.qualifiers.ActivityContext
import java.text.SimpleDateFormat
import java.time.Month
import java.util.*
import javax.inject.Inject

class DateItemsAdapter constructor(private val context: Context, private val dateItemsList: List<DateGroup>) :
    RecyclerView.Adapter<DateItemsAdapter.DateItemViewHolder>() {
    class DateItemViewHolder(itemView: View, val binding: DatesItemBinding) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateItemViewHolder {
        val binding = DatesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DateItemViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: DateItemViewHolder, position: Int) {
        val dateItemEntity = dateItemsList[position]
        holder.binding.tvDateAndDay.text = "${dateItemEntity.dayOfWeek}, ${dateItemEntity.dayOfMonth}"
        holder.binding.tvTotalEntries.text = "${dateItemEntity.items.size} Entries"
        holder.binding.recNotes.adapter = NotesItemAdapter(context, dateItemEntity.items)
    }

    override fun getItemCount(): Int {
        return dateItemsList.size
    }
}