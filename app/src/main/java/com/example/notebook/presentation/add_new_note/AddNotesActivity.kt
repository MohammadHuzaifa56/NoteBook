package com.example.notebook.presentation.add_new_note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.notebook.databinding.ActivityAddNotesBinding
import com.example.notebook.domain.model.NotesItemEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
class AddNotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNotesBinding
    private val addNotesViewModel by viewModels<AddNotesViewModel>()
    private var mood: String = "GOOD"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rgMood.setOnCheckedChangeListener { group, checkedId ->
            mood = if (checkedId == binding.rbGood.id) {
                "GOOD"
            } else if (checkedId == binding.rbBad.id) {
                "BAD"
            } else {
                "NORMAL"
            }
        }

        binding.btnSave.setOnClickListener {
            val notesItemEntity = NotesItemEntity(
                text = binding.etNotes.text.toString(),
                date = Calendar.getInstance().time,
                mood = mood
            )
            CoroutineScope(Dispatchers.Main).launch {
                addNotesViewModel.insertNoteItem(notesItemEntity)
                finish()
            }
        }
    }
}