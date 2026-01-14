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
data class PublishedSongs (//input the fields
    @PrimaryKey(autoGenerate = false)
    val published_id: Int,
    val created_id: Int,
    val views: Int,
    val rating: Double,
    val date_published: Date,
    val is_completed: Boolean
)
