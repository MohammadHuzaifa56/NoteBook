package com.example.notebook.utils

import java.text.SimpleDateFormat
import java.util.*

class AppHelper {
    companion object {
        fun getFormattedDate(date: Date): String? {
            val simpleDateTimeFormat = SimpleDateFormat("HH:mm", Locale.US)
            return simpleDateTimeFormat.format(date)
        }
    }
}