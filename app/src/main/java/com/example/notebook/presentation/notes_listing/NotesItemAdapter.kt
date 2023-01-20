package com.example.notebook.presentation.notes_listing

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook.R
import com.example.notebook.databinding.GroupedNotesItemsBinding
import com.example.notebook.databinding.NotesItemBinding
import com.example.notebook.domain.enum.MoodStates
import com.example.notebook.domain.model.MonthGroup
import com.example.notebook.domain.model.NotesItemEntity
import com.example.notebook.presentation.notes_detail.NotesDetailActivity
import com.example.notebook.utils.AppHelper
import java.util.*
import javax.inject.Inject

class NotesItemAdapter constructor(private val context: Context, private val notesItemList: List<NotesItemEntity>) :
    RecyclerView.Adapter<NotesItemAdapter.NotesItemViewHolder>() {
    class NotesItemViewHolder(itemView: View, val binding: NotesItemBinding) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesItemViewHolder {
        val binding = NotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesItemViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: NotesItemViewHolder, position: Int) {
        val notesItemEntity = notesItemList[position]
        holder.binding.tvNotesContent.text = notesItemEntity.text
        holder.binding.tvDate.text = AppHelper.getFormattedDate(notesItemEntity.date)
        holder.binding.tvMoodState.apply {
            when (notesItemEntity.mood) {
                MoodStates.GoodDay.state -> {
                    setTextColor(context.getColor(R.color.green))
                    text = "GOOD"
                }
                MoodStates.BadDay.state -> {
                    text = "BAD"
                    setTextColor(context.getColor(R.color.red))
                }
                MoodStates.NormalDay.state -> {
                    text = "NORMAl"
                    setTextColor(context.getColor(R.color.yellow))
                }
            }
        }

        holder.itemView.setOnClickListener{
            val intent = Intent(context,NotesDetailActivity::class.java)
            intent.putExtra("noteId", notesItemEntity.id)
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return notesItemList.size
    }
}