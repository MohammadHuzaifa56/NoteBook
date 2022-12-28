package com.example.notebook.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notebook.domain.model.NotesItemEntity

@Database(
    entities = [NotesItemEntity::class],
    version = 1
)
abstract class NotesDatabase: RoomDatabase() {
    abstract val dao: NotesDao
}