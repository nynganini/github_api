package org.longevityintime.githubapi.database

import androidx.room.TypeConverter
import org.longevityintime.githubapi.model.DateSerializer
import java.text.SimpleDateFormat
import java.util.*

class Converters {
    private val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

    @TypeConverter
    fun toDate(timestamp: String): Date {
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)
        return dateFormat.parse(timestamp)!!
    }
    @TypeConverter
    fun fromDate(date: Date): String {
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)
        return dateFormat.format(date)
    }
}