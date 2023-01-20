package com.example.notebook.domain.model

data class MonthGroup(val month: String, val avgMood:Int, val totalEntries: Int, val dateGroups: List<DateGroup>)
data class DateGroup(val dayOfWeek: String, val dayOfMonth: Int, val items: List<NotesItemEntity>)
