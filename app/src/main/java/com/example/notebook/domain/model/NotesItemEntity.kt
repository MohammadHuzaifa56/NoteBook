package com.example.notebook.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class NotesItemEntity(
    @PrimaryKey
    val id: Int? = null,

    val text: String,

    val date: Date,

    val mood: Int
)
