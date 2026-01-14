package com.example.hope.data.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(entity = Songs::class,
            parentColumns = ["song_id"],
            childColumns = ["song_id"]),
        ForeignKey(entity = Users::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"])
    ]
) //create an entity that will be used as a table in the database
data class CreatedSongs (//input the fields
    @PrimaryKey(autoGenerate = false)
    val created_id: Int,
    val song_id: Int,
    val user_id: Int,
    val difficulty: Int,
    val midi: String,
    val tempo: Int,
    val description: String,
    val preferred_xml: Int //stores an id of the xml displayed
)
