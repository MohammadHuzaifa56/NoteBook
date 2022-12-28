package com.example.notebook.data.repository

import com.example.notebook.data.local.NotesDao
import com.example.notebook.data.local.NotesDatabase
import com.example.notebook.domain.model.NotesItemEntity
import com.example.notebook.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepositoryImpl @Inject constructor(
    db: NotesDatabase
) : NotesRepository {
    private val dao: NotesDao = db.dao

    override suspend fun insertNote(notesItemEntity: NotesItemEntity) {
        dao.insertNoteEntity(notesItemEntity)
    }

    override suspend fun getNotesList(): Flow<List<NotesItemEntity>> {
        return flow {
            emit(dao.getNoteEntity())
        }
    }

    override suspend fun getNotesDetail(id: Int): Flow<NotesItemEntity> {
        return flow {
            emit(dao.getNotesDetail(id))
        }
    }
}