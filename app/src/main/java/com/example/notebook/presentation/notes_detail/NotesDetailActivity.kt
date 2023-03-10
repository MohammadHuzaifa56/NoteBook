package com.example.notebook.presentation.notes_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.notebook.R
import com.example.notebook.databinding.ActivityAddNotesBinding
import com.example.notebook.databinding.ActivityNotesDetailBinding
import com.example.notebook.domain.enum.MoodStates
import com.example.notebook.presentation.notes_listing.NotesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class NotesDetailActivity : AppCompatActivity() {
    var notesId: Int = 0
    val notesDetailViewModel by viewModels<NotesDetailViewModel>()
    private lateinit var binding: ActivityNotesDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notesId = intent.getIntExtra("noteId", 0)

        lifecycleScope.launchWhenStarted {
            notesDetailViewModel.getNotesDetail(notesId).collect {
                binding.tvNotesContent.text = it.text
                binding.tvMood.apply {
                    when (it.mood) {
                        MoodStates.GoodDay.state -> {
                            setTextColor(getColor(R.color.green))
                            text = "GOOD"
                        }
                        MoodStates.BadDay.state -> {
                            text = "BAD"
                            setTextColor(getColor(R.color.red))
                        }
                        MoodStates.NormalDay.state -> {
                            text = "NORMAl"
                            setTextColor(getColor(R.color.yellow))
                        }
                    }
                }
            }
        }

    }
}