package com.example.notebook.presentation.notes_listing

import androidx.lifecycle.*
import com.example.notebook.data.repository.NotesRepository
import com.example.notebook.domain.model.DateGroup
import com.example.notebook.domain.model.MonthGroup
import com.example.notebook.domain.model.NotesItemEntity
import com.example.notebook.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class NotesListViewModel @Inject constructor(
    val notesRepository: NotesRepository
): ViewModel() {
    fun getNotesList(): Flow<Resource> = flow {
        emit(Resource.Loading)
        try {
            notesRepository.getNotesList().collect {items->
                val calendar = Calendar.getInstance()
                val groupedItems = items.groupBy {
                    calendar.time = it.date
                    calendar.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.getDefault())
                }.mapValues {
                    val (month, dateList) = it
                    var avgMood = 0
                    val dateGroup = dateList.groupBy {
                        avgMood += it.mood
                        calendar.time = it.date
                        calendar.get(Calendar.DAY_OF_MONTH)
                    }.map { (day, listItems) ->
                        calendar.set(Calendar.DAY_OF_MONTH, day)
                        val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG,Locale.getDefault())
                        DateGroup(dayOfWeek,day,listItems)
                    }
                    MonthGroup(month = month,avgMood.div(dateList.size.toFloat()).roundToInt(), dateList.size, dateGroup)
                }
                val groupedNotesItems = groupedItems.map { (month, items)->
                    MonthGroup(items.month,items.avgMood, items.totalEntries,items.dateGroups)
                }
                emit(Resource.Success(groupedNotesItems))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }
}