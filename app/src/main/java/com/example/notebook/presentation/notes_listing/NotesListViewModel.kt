package com.example.notebook.presentation.notes_listing

import androidx.lifecycle.*
import com.example.notebook.data.repository.NotesRepository
import com.example.notebook.domain.model.NotesItemEntity
import com.example.notebook.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(
    val notesRepository: NotesRepository
): ViewModel() {
    fun getNotesList(): Flow<Resource> = flow {
        emit(Resource.Loading)
        try {
            notesRepository.getNotesList().collect {
                emit(Resource.Success(it))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }
}