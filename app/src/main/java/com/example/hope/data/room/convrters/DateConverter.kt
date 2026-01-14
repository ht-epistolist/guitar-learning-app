package com.example.hope.data.room.convrters

import androidx.room.TypeConverter
import java.util.Date

//open class so allow the inheritance (as the classes are final by default)
open class DateConverter {//create the date to int converter to allow room to store complex data
    @TypeConverter
    fun LongToDate(longDate: Long?): Date?{
        return longDate?.let{ Date(it) }
    }

    @TypeConverter
    fun DateToLong(dateDate: Date?): Long?{
        return dateDate?.time
    }
}