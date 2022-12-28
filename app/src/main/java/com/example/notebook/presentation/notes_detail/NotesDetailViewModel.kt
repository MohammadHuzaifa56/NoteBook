package com.example.notebook.presentation.notes_detail

import androidx.lifecycle.ViewModel
import com.example.notebook.data.repository.NotesRepository
import com.example.notebook.domain.model.NotesItemEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class NotesDetailViewModel @Inject constructor(
    val notesRepository: NotesRepository
): ViewModel() {
    fun getNotesDetail(id: Int): Flow<NotesItemEntity> = flow {
        notesRepository.getNotesDetail(id).collect {
            emit(it)
        }
    }
}