package com.example.hope.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity //create an entity that will be used as a table in the database
data class Genres (//input the fields
    @PrimaryKey(autoGenerate = false)
    val genre_id: Int,
    val genre_constants: String,
)
