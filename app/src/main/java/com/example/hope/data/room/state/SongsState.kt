package com.example.hope.data.room.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.hope.data.room.entity.CreatedSongs
import com.example.hope.data.room.entity.CurrentUser

data class SongsState (
    val songs: List<CreatedSongs> = emptyList(),
    val title: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf("")
)