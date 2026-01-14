package com.example.hope.data.room.event


//reacting on users actions
sealed interface CurrentUserEvent {
    object SaveCurrentUser: CurrentUserEvent //adds user to a local database
    data class SetUsername(val username: String): CurrentUserEvent
    data class SetName(val name: String): CurrentUserEvent
    data class SetPassword(val password: String): CurrentUserEvent
    data class SetEmail(val email: String): CurrentUserEvent
}