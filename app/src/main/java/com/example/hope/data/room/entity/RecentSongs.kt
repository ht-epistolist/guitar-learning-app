package com.example.hope.data.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(entity = PublishedSongs::class,
            parentColumns = ["published_id"],
            childColumns = ["published_id"])
    ]
) //create an entity that will be used as a table in the database
data class RecentSongs (//input the fields
    @PrimaryKey(autoGenerate = true)
    val recentsongs_id: Int,
    val published_id: Int,
    val progress: Int,
    val custom_tempo: Int,
    val last_accessed: Int
)//separate from the SavedSongs as is searched to delete ones that weren't accessed for the set time
