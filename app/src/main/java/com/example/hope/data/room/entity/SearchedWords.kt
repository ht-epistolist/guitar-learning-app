package com.example.hope.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity //create an entity that will be used as a table in the database
data class SearchedWords (//input the
    @PrimaryKey(autoGenerate = true)
    val searchedwords_id: Int,
    val searched_word: String,
    val date_searched: Date
)
