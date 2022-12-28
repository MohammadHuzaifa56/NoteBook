package com.example.notebook.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notebook.domain.model.NotesItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteEntity(
        notesItemEntity: NotesItemEntity
    )

    @Query("SELECT * FROM notesitementity")
    suspend fun getNoteEntity(): List<NotesItemEntity>

    @Query("SELECT * FROM notesitementity WHERE :noteId == id")
    suspend fun getNotesDetail(noteId: Int): NotesItemEntity

}