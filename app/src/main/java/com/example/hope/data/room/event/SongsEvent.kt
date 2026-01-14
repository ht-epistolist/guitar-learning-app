package com.example.hope.data.room.event


//reacting on users actions
sealed interface SongsEvent {
    object SaveCurrentUser: SongsEvent //adds user to a local database
    data class SetUsername(val username: String): SongsEvent
    data class SetName(val name: String): SongsEvent
    data class SetPassword(val password: String): SongsEvent
    data class SetEmail(val email: String): SongsEvent
}