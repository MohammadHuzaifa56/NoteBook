package com.example.notebook

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.notebook.data.local.NotesDao
import com.example.notebook.data.local.NotesDatabase
import com.example.notebook.domain.model.NotesItemEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotesDaoTest {
    private lateinit var notesDao: NotesDao
    private lateinit var db: NotesDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, NotesDatabase::class.java).allowMainThreadQueries().build()
        notesDao = db.dao
    }

    @After
    fun tearUp() {
        db.close()
    }

    @Test
    fun insertAndGetNote_Test() = runBlocking{
        val noteItem = NotesItemEntity(1,"This is test Note",1672146484717,"GOOD")
        notesDao.insertNoteEntity(noteItem)

        val notesList = notesDao.getNoteEntity()
        Assert.assertEquals(1, notesList.size)
        Assert.assertEquals("This is test Note", notesList[0].text)
    }
}