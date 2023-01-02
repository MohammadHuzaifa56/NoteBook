package com.example.notebook.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notebook.domain.DateConverter
import com.example.notebook.domain.model.NotesItemEntity

@Database(
    entities = [NotesItemEntity::class],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class NotesDatabase : RoomDatabase() {
    abstract val dao: NotesDao
}