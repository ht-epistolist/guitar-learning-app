package com.example.hope.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hope.data.room.event.CurrentUserEvent

@Entity //create an entity that will be used as a table in the database
data class CurrentUser (//input the fields
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(defaultValue = "0") val user_id: Int,
    @ColumnInfo(defaultValue = "JustAPerson") val username: String,
    @ColumnInfo(defaultValue = "Imya") val name: String,
    @ColumnInfo(defaultValue = "0") val level: Double,
    @ColumnInfo(defaultValue = "0") val reputation: Int,
    @ColumnInfo(defaultValue = "0") val day_progress: Double,
    @ColumnInfo(defaultValue = "1") val keep_searched_MAX: Int,
    @ColumnInfo(defaultValue = "1") val keep_recent_MAX: Int,
    @ColumnInfo(defaultValue = "0") val day_restart_time: Int,
    @ColumnInfo(defaultValue = "1") val keep_user_MAX: Int,
    val password: String
)//a separate from the users table to not involve the user during search operations
