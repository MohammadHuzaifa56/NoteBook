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
        calendar[Calendar.YEAR] = 2023
        calendar[Calendar.MONTH] = Calendar.JANUARY
        calendar[Calendar.DAY_OF_MONTH] = 2
        val formattedDate: String? = AppHelper.getFormattedDate(calendar.time)
        Assert.assertEquals("02 Jan 2023", formattedDate)
    }

    @Test
    fun getFromattedDate_Test_Wrong() {
        val calendar = Calendar.getInstance()
        calendar[Calendar.YEAR] = 2023
        calendar[Calendar.MONTH] = Calendar.JANUARY
        calendar[Calendar.DAY_OF_MONTH] = 2
        val formattedDate: String? = AppHelper.getFormattedDate(calendar.time)
        Assert.assertNotEquals("Jan 02 2023", formattedDate)
    }
}