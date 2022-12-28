package com.example.notebook.utils

import com.example.notebook.domain.model.NotesItemEntity

sealed class Resource{
    object Loading : Resource()
    data class Success(val data: List<NotesItemEntity>) : Resource()
    data class Error(val message: String) : Resource()
}