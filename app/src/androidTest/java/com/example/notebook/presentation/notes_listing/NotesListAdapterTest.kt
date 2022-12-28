package com.example.notebook.presentation.notes_listing

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.util.*
import javax.inject.Inject
import com.example.notebook.R


@HiltAndroidTest
class NotesListAdapterTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    val context = ApplicationProvider.getApplicationContext<Context>()

    @Inject
    lateinit var notesListAdapter: NotesListAdapter
    @Test
    fun getMoodColor_Test() {
        val colorId = notesListAdapter.getMoodStateColor("GOOD")
        assertEquals(context.getColor(R.color.green), colorId)
    }

    @Test
    fun getFormattedDate_Test() {
        val dateFormatted = notesListAdapter.getFormattedDate(Date("1672146484717"))
        assertEquals("27 Dec 2022", dateFormatted)
    }
}