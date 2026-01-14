package com.example.hope.data.room.state

import com.example.hope.data.room.entity.CurrentUser

data class CurrentUserState (
    val currentUser: List<CurrentUser> = emptyList(),
    val username: String = "",
    val name: String = "",
    val password: String = "",
    val email: String = "",
)