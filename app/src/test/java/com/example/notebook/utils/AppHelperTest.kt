package com.example.notebook.utils

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import java.util.*

class AppHelperTest{
    @Test
    fun getFromattedDate_Test_Correct() {
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR] = 10
        calendar[Calendar.MINUTE] = 50
        val formattedDate: String? = AppHelper.getFormattedDate(calendar.time)
        Assert.assertEquals("10:50", formattedDate)
    }

    @Test
    fun getFromattedDate_Test_Wrong() {
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR] = 10
        calendar[Calendar.MINUTE] = 50
        val formattedDate: String? = AppHelper.getFormattedDate(calendar.time)
        Assert.assertNotEquals("22:50", formattedDate)
    }
}