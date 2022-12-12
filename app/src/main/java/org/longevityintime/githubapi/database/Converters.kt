package org.longevityintime.githubapi.database

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun toDate(timestamp: String): Date {
        return Date()
    }
    @TypeConverter
    fun fromDate(date: Date): String {
        return ""
    }
}