package com.example.hope.data.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    foreignKeys = [
        ForeignKey(entity = PublishedSongs::class,
            parentColumns = ["published_id"],
            childColumns = ["published_id"])
    ]
) //create an entity that will be used as a table in the database
data class SearchedSongs (//input the fields
    @PrimaryKey(autoGenerate = true)
    val searchedsongs_id: Int,
    val published_id: Int,
    val date_searched: Date
)
