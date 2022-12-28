package com.example.notebook.presentation.add_new_note

import androidx.lifecycle.ViewModel
import com.example.notebook.data.repository.NotesRepository
import com.example.notebook.domain.model.NotesItemEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNotesViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {
    suspend fun insertNoteItem(notesItemEntity: NotesItemEntity) {
        notesRepository.insertNote(notesItemEntity)
    }
}