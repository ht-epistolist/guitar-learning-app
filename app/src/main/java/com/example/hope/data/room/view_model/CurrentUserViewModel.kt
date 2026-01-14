package com.example.hope.data.room.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hope.data.room.dao.CurrentUserDao
import com.example.hope.data.room.entity.CurrentUser
import com.example.hope.data.room.event.CurrentUserEvent
import com.example.hope.data.room.state.CurrentUserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CurrentUserViewModel(
    private val dao: CurrentUserDao
): ViewModel(){
    private val _state = MutableStateFlow(CurrentUserState()) //get the state
    private val _currentUser = dao.getCurrentUser() //get the current user
    val state = combine(_state, _currentUser){state, currentUser ->
        state.copy(//executed when state or current user have a new value
            currentUser = currentUser //updates current user
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CurrentUserState())

    fun onEvent(event: CurrentUserEvent){ //function to be triggered with a corresponding event
        when(event){
            CurrentUserEvent.SaveCurrentUser -> {
                val username = state.value.username
                val name = state.value.name
                val password = state.value.password
                val email = state.value.email
                if(email.isBlank() || password.isBlank()){ //check if email and hash exist to insert valid data to insert
                    return //finish the function if nothing to insert
                }
                val currentUser = CurrentUser(0,username,name,0.0,0,0.0,1,1,0,1, password)
                viewModelScope.launch { //add the current user the database
                    dao.upsertCurrentUser(currentUser)
                }
                _state.update { it.copy( //return to initial values when inserted
                    username = "",
                    name = "",
                    password = "",
                    email = ""
                ) }
            }
            is CurrentUserEvent.SetName -> { //add the name
                _state.update { it.copy(
                    name = event.name
                ) }
            }
            is CurrentUserEvent.SetUsername -> { //add the username
                _state.update { it.copy(
                    username = event.username
                ) }
            }
            is CurrentUserEvent.SetPassword -> {//add the hash of the password
                _state.update { it.copy(
                    password = event.password
                ) }
            }
            is CurrentUserEvent.SetEmail -> {//add the email
                _state.update { it.copy(
                    email = event.email
                ) }
            }
        }
    }
}