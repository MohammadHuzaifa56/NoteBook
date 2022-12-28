package com.example.notebook.data.repository

import com.example.notebook.domain.model.NotesItemEntity
import com.example.notebook.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun insertNote(notesItemEntity: NotesItemEntity)
    suspend fun getNotesList(): Flow<List<NotesItemEntity>>
    suspend fun getNotesDetail(id: Int): Flow<NotesItemEntity>
}