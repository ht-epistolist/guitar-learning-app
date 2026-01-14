package com.example.hope.data.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(entity = Genres::class,
            parentColumns = ["genre_id"],
            childColumns = ["genre_id"])
    ]
) //create an entity that will be used as a table in the database
data class Songs (//input the fields
    @PrimaryKey(autoGenerate = false) //define primary key, disabling autoincrement generation (as the entities are synced from a global database)
    val song_id: Int,
    val title: String,
    val artist: String,
    val duration: Int,
    val link: String,
    val genre_id: String,
)
