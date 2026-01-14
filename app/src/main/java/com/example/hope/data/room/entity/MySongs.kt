package com.example.hope.data.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    foreignKeys = [
        ForeignKey(entity = CreatedSongs::class,
            parentColumns = ["created_id"],
            childColumns = ["created_id"])
    ]
) //create an entity that will be used as a table in the database
data class MySongs (//input the fields
    @PrimaryKey(autoGenerate = true)
    val mysongs_id: Int,
    val created_id: Int,
    val number_of_changes: Int,
    val last_edited: Date,
    val is_pubished: Boolean,
)
