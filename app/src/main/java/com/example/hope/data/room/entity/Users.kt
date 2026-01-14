package com.example.hope.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity //create an entity that will be used as a table in the database
data class Users (//input the fields
    @PrimaryKey(autoGenerate = false)
    val user_id: Int,
    val username: String,
    val name: String,
    val level: Int,
    val reputation: Int
)
